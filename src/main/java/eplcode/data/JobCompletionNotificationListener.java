package eplcode.data;

import eplcode.model.Team;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
    private final EntityManager em;

    @Autowired
    public JobCompletionNotificationListener(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            Map<String, Team> teamData = new HashMap<>();

            em.createQuery("select m.homeTeam, count(*) from Match m group by m.homeTeam", Object[].class)
                    .getResultList()
                    .stream()
                    .map(e -> new Team((String) e[0], (long) e[1]))
                    .forEach(team -> teamData.put(team.getTeamName(), team));

            em.createQuery("select m.awayTeam, count(*) from Match m group by m.awayTeam", Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(e -> {
                        Team team = teamData.get((String) e[0]);
                        team.setTotalMatches(team.getTotalMatches() + (long) e[1]);
                    });

            em.createQuery("select m.homeTeam, m.awayTeam, m.fullTimeHomeTeamGoals, m.fullTimeAwayTeamGoals from Match m", Object[].class)
                    .getResultList()
                    .forEach(e -> {
                        String homeTeam = (String) e[0];
                        String awayTeam = (String) e[1];
                        int homeTeamGoals = (int) e[2];
                        int awayTeamGoals = (int) e[3];

                        Team home = teamData.getOrDefault(homeTeam, new Team(homeTeam));
                        Team away = teamData.getOrDefault(awayTeam, new Team(awayTeam));

                        if (homeTeamGoals > awayTeamGoals) {
                            home.setTotalWins(home.getTotalWins() + 1);
                            away.setTotalLosses(away.getTotalLosses() + 1);
                        } else if (homeTeamGoals < awayTeamGoals) {
                            away.setTotalWins(away.getTotalWins() + 1);
                            home.setTotalLosses(home.getTotalLosses() + 1);
                        } else {
                            home.setTotalDraws(home.getTotalDraws() + 1);
                            away.setTotalDraws(away.getTotalDraws() + 1);
                        }
                        teamData.put(homeTeam, home);
                        teamData.put(awayTeam, away);

                    });

            teamData.values().forEach(team -> em.persist(team));
            teamData.values().forEach(team -> System.out.println(team));
        }

    }

}