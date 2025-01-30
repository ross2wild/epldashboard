import { React, useEffect, useState } from 'react';
import {Link, useParams} from 'react-router-dom';
import {MatchDetailCard} from "../components/MatchDetailCard";
import {MatchSmallCard} from "../components/MatchSmallCard";
import './TeamPage.scss';
import {PieChart} from "@mui/x-charts";


export const TeamPage = () => {

    const [team, setTeam] = useState({matches: []});
    const { teamName } = useParams();

    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch(`http://localhost:8080/team/${teamName}`)
                const data = await response.json();
                setTeam(data);
            };
            fetchMatches();
        },[teamName]

    );


    if (!team || !team.teamName){
        return <h1>Team not found</h1>;
    }
    return (
        <div className="TeamPage">
            <div className="team-name-section">
                <h1 className="team-name">{team.teamName}</h1>
            </div>
            <div className="win-draw-loss-section">
                <PieChart
                    series={[
                        {
                            data: [
                                { title: 0, value: team.totalWins, label: 'Wins', color: '#27AF27B2' },
                                { title: 1, value: team.totalDraws, label: 'Draws', color: '#AFAFAFFF' },
                                { title: 2, value: team.totalLosses, label: 'Losses', color: '#DE2525E8'},
                            ],
                            innerRadius:25,
                            outerRadius: 50,
                            paddingAngle: 8,
                            cornerRadius: 5,
                            startAngle: 0,
                        },
                    ]}
                    width={230}
                    height={140}
                />
            </div>

            <div className="match-section-container">
                <div className="match-detail-section">
                    <h3 className="recent-premier-matches">Most recent Premier League Matches</h3>
                    <MatchDetailCard teamName = {team.teamName} match = {team.matches[0]}/>
                </div>
                {team.matches.slice(1).map(match => <MatchSmallCard teamName = {team.teamName} match={match}/>)}
                <div className="more-link">
                    <Link to={`/teams/${teamName}/matches/${process.env.REACT_APP_DATA_END_YEAR}`}>More</Link>
                </div>
            </div>

        </div>
      );
    }

export default TeamPage;

