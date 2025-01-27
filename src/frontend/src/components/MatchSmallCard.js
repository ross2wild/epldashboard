import { React } from 'react';
import { Link } from 'react-router-dom';
import './MatchSmallCard.scss';


export const MatchSmallCard = ({match, teamName}) => {

    const otherTeam = match.homeTeam === teamName ? match.awayTeam : match.homeTeam;
    const otherTeamRoute = `/teams/${otherTeam}`;
    const isMatchWon =
        (match.fullTimeResult === "H" && match.homeTeam === teamName) ||
        (match.fullTimeResult === "A" && match.awayTeam === teamName);
    const isMatchDraw = match.fullTimeResult === "D";

    return (
        <div
            className={`MatchSmallCard ${
                isMatchDraw ? "draw-card" : isMatchWon ? "won-card" : "lost-card"
            }`}
        >
            <h4 className="match-date">{match.date}</h4>
            <div className="match-teams">
                {/* Home Team and Score */}
                <div className="team-info">
        <span className="team-name">
          {match.homeTeam === teamName ? (
              match.homeTeam
          ) : (
              <Link to={otherTeamRoute}>{match.homeTeam}</Link>
          )}
        </span>
                    <span className="team-score">{match.fullTimeHomeTeamGoals}</span>
                </div>
                {/* Away Team and Score */}
                <div className="team-info">
        <span className="team-name">
          {match.awayTeam === teamName ? (
              match.awayTeam
          ) : (
              <Link to={otherTeamRoute}>{match.awayTeam}</Link>
          )}
        </span>
                    <span className="team-score">{match.fullTimeAwayTeamGoals}</span>
                </div>
            </div>
        </div>
    );
}