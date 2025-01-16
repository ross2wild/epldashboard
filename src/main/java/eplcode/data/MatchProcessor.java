package eplcode.data;
import eplcode.model.Match;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.time.LocalTime;

public class MatchProcessor implements ItemProcessor<MatchInput, Match> {
    private static final Logger log = LoggerFactory.getLogger(MatchProcessor.class);

        @Override
        public Match process(final MatchInput matchInput) {

            Match match = new Match();
            match.setMatchID(matchInput.getMatchID());
            match.setSeason(matchInput.getSeason());
            match.setMatchWeek(Integer.parseInt(matchInput.getMatchWeek()));
            match.setDate(LocalDate.parse(matchInput.getDate()));
            match.setTime(LocalTime.parse(matchInput.getTime()));
            match.setHomeTeam(matchInput.getHomeTeam());
            match.setAwayTeam(matchInput.getAwayTeam());
            match.setFullTimeHomeTeamGoals(Integer.parseInt(matchInput.getFullTimeHomeTeamGoals()));
            match.setFullTimeAwayTeamGoals(Integer.parseInt(matchInput.getFullTimeAwayTeamGoals()));
            match.setFullTimeResult(matchInput.getFullTimeResult());
            match.setHalfTimeHomeTeamGoals(Integer.parseInt(matchInput.getHalfTimeHomeTeamGoals()));
            match.setHalfTimeAwayTeamGoals(Integer.parseInt(matchInput.getHalfTimeAwayTeamGoals()));
            match.setHalfTimeResult(matchInput.getHalfTimeResult());
            match.setReferee(matchInput.getReferee());
            match.setHomeTeamShots(Integer.parseInt(matchInput.getHomeTeamShots()));
            match.setAwayTeamShots(Integer.parseInt(matchInput.getAwayTeamShots()));
            match.setHomeTeamShotsOnTarget(Integer.parseInt(matchInput.getHomeTeamShotsOnTarget()));
            match.setAwayTeamShotsOnTarget(Integer.parseInt(matchInput.getAwayTeamShotsOnTarget()));
            match.setHomeTeamCorners(Integer.parseInt(matchInput.getHomeTeamCorners()));
            match.setAwayTeamCorners(Integer.parseInt(matchInput.getAwayTeamCorners()));
            match.setHomeTeamFouls(Integer.parseInt(matchInput.getHomeTeamFouls()));
            match.setAwayTeamFouls(Integer.parseInt(matchInput.getAwayTeamFouls()));
            match.setHomeTeamYellowCards(Integer.parseInt(matchInput.getHomeTeamYellowCards()));
            match.setAwayTeamYellowCards(Integer.parseInt(matchInput.getAwayTeamYellowCards()));
            match.setHomeTeamRedCards(Integer.parseInt(matchInput.getHomeTeamRedCards()));
            match.setAwayTeamRedCards(Integer.parseInt(matchInput.getAwayTeamRedCards()));
            match.setHomeTeamPoints(Integer.parseInt(matchInput.getHomeTeamPoints()));
            match.setAwayTeamPoints(Integer.parseInt(matchInput.getAwayTeamPoints()));

            return match;
        }

}

