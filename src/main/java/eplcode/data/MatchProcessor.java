package eplcode.data;
import eplcode.model.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class MatchProcessor implements ItemProcessor<MatchInput, Match> {
    private static final Logger log = LoggerFactory.getLogger(MatchProcessor.class);

        @Override
        public Match process(final MatchInput matchInput) throws Exception {

            Match match = new Match();
            match.setMatchID(matchInput.getMatchID());
            match.setSeason(matchInput.getSeason_End_Year());
            match.setMatchWeek(matchInput.getWk());
            match.setDate(LocalDate.parse(matchInput.getDate()));
            match.setHomeTeam(matchInput.getHome());
            match.setAwayTeam(matchInput.getAway());
            match.setFullTimeHomeTeamGoals(matchInput.getHomeGoals());
            match.setFullTimeAwayTeamGoals(matchInput.getAwayGoals());
            match.setFullTimeResult(matchInput.getFTR());
            return match;
        }

}

