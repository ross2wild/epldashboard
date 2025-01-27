import { React } from 'react';
import {Link} from "react-router-dom";
import "./MatchDetailCard.scss";

export const MatchDetailCard = ({teamName, match}) => {

    const otherTeam = match.homeTeam === teamName ? match.awayTeam : match.homeTeam;
    const otherTeamRoute = `/teams/${otherTeam}`;
    const isMatchWon =
        (match.fullTimeResult === "H" && match.homeTeam === teamName) ||
        (match.fullTimeResult === "A" && match.awayTeam === teamName);
    const isMatchDraw = match.fullTimeResult === "D";

    if(!match) return null;
    return (
        <div className={`MatchDetailCard ${
            isMatchDraw ? "draw-card" : isMatchWon ? "won-card" : "lost-card"}`}>
            <h4 className="match-date">{match.date}</h4>
            <h1 className="match-result">
                {/* if home team is not the main team, make it a link*/}
                {match.homeTeam === teamName ? (match.homeTeam) : (<Link to={otherTeamRoute}>{match.homeTeam}</Link>)}
                {" "}
                {match.fullTimeHomeTeamGoals}
                {" - "}
                {match.fullTimeAwayTeamGoals}
                {" "}
                {/* if away team is not the main team, make it a link*/}
                {match.awayTeam === teamName ? (match.awayTeam) : (<Link to={otherTeamRoute}>{match.awayTeam}</Link>)}
            </h1>
        </div>
    );
}