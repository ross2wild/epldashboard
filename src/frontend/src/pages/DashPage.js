import { React, useEffect, useState } from 'react';
import './DashPage.scss';
import {TeamTile} from "../components/TeamTile";

export const DashPage = () => {

    const [team, setTeam] = useState([]);

    useEffect(
        () => {
            const fetchAllTeams = async () => {
                const response = await fetch(`${process.env.REACT_APP_API_ROOT_URL}/team/`)
                const data = await response.json();
                const sortedTeam = data.sort((a, b) => a.teamName.localeCompare(b.teamName));

                setTeam(sortedTeam)
            };
            fetchAllTeams();
        },[]

    );

    return (
        <div className="DashPage">
            <div className="header-section">
                <h1 className="app-name">The Premier League Dashboard</h1>
            </div>
            <div className="team-grid">
                { team.map(team => <TeamTile key={team.id} teamName={team.teamName} />)}
            </div>
        </div>
    );
}

export default DashPage;
