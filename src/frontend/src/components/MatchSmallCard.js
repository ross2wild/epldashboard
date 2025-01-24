import { React } from 'react';
import { Link } from 'react-router-dom';


export const MatchSmallCard = ({match, teamName}) => {

    const otherTeam = match.homeTeam === teamName ? match.awayTeam : match.homeTeam;
    const otherTeamRoute = `/teams/${otherTeam}`;

    return (
        <div className="MatchSmallCard">
            <h4>{match.date}</h4>

            <h3>
                {/* if home team is not the main team, make it a link*/}
                {match.homeTeam === teamName ? (match.homeTeam) : (<Link to={otherTeamRoute}>{match.homeTeam}</Link>)}
                {" "}
                {match.fullTimeHomeTeamGoals}
                {" - "}
                {match.fullTimeAwayTeamGoals}
                {" "}
                {/* if away team is not the main team, make it a link*/}
                {match.awayTeam === teamName ? (match.awayTeam) : (<Link to={otherTeamRoute}>{match.awayTeam}</Link>)}

            </h3>
        </div>
    );
}