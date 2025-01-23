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
            match.setSeason(matchInput.getSeason());
            match.setMatchWeek(parseIntegerWithDefault(matchInput.getMatchWeek(), -1));
            match.setDate(LocalDate.parse(matchInput.getDate()));

            //validate and handle getTime
            String timeInput = matchInput.getTime();
            if (timeInput == null || timeInput.isEmpty()) {
                match.setTime(null);
            } else {
                try {
                    match.setTime(LocalTime.parse(timeInput));
                } catch (DateTimeParseException e){
                    match.setTime(null);
                }
            }

            // Set other fields with validation
            match.setHomeTeam(matchInput.getHomeTeam());
            match.setAwayTeam(matchInput.getAwayTeam());
            match.setFullTimeHomeTeamGoals(parseIntegerWithDefault(matchInput.getFullTimeHomeTeamGoals(), 0));
            match.setFullTimeAwayTeamGoals(parseIntegerWithDefault(matchInput.getFullTimeAwayTeamGoals(), 0));
            match.setFullTimeResult(matchInput.getFullTimeResult());
            match.setHalfTimeHomeTeamGoals(parseIntegerWithDefault(matchInput.getHalfTimeHomeTeamGoals(), 0));
            match.setHalfTimeAwayTeamGoals(parseIntegerWithDefault(matchInput.getHalfTimeAwayTeamGoals(), 0));
            match.setHalfTimeResult(matchInput.getHalfTimeResult());
            match.setReferee(matchInput.getReferee());
            match.setHomeTeamShots(parseIntegerWithDefault(matchInput.getHomeTeamShots(), 0));
            match.setAwayTeamShots(parseIntegerWithDefault(matchInput.getAwayTeamShots(), 0));
            match.setHomeTeamShotsOnTarget(parseIntegerWithDefault(matchInput.getHomeTeamShotsOnTarget(), 0));
            match.setAwayTeamShotsOnTarget(parseIntegerWithDefault(matchInput.getAwayTeamShotsOnTarget(), 0));
            match.setHomeTeamCorners(parseIntegerWithDefault(matchInput.getHomeTeamCorners(), 0));
            match.setAwayTeamCorners(parseIntegerWithDefault(matchInput.getAwayTeamCorners(), 0));
            match.setHomeTeamFouls(parseIntegerWithDefault(matchInput.getHomeTeamFouls(), 0));
            match.setAwayTeamFouls(parseIntegerWithDefault(matchInput.getAwayTeamFouls(), 0));
            match.setHomeTeamYellowCards(parseIntegerWithDefault(matchInput.getHomeTeamYellowCards(), 0));
            match.setAwayTeamYellowCards(parseIntegerWithDefault(matchInput.getAwayTeamYellowCards(), 0));
            match.setHomeTeamRedCards(parseIntegerWithDefault(matchInput.getHomeTeamRedCards(), 0));
            match.setAwayTeamRedCards(parseIntegerWithDefault(matchInput.getAwayTeamRedCards(), 0));
            match.setHomeTeamPoints(parseIntegerWithDefault(matchInput.getHomeTeamPoints(), 0));
            match.setAwayTeamPoints(parseIntegerWithDefault(matchInput.getAwayTeamPoints(), 0));

            return match;
        }

    private int parseIntegerWithDefault(String input, int defaultValue) {
        if (input == null || input.isEmpty()) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

}

