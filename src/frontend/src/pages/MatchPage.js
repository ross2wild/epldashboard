import { React, useEffect, useState } from 'react';
import {useParams} from 'react-router-dom';
import {MatchDetailCard} from "../components/MatchDetailCard";

import './MatchPage.scss';
import { YearSelector } from '../components/YearSelector';



export const MatchPage = () => {


    const [matches, setMatch] = useState([]);
    const {teamName, season} = useParams();

    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch(`${process.env.REACT_APP_API_ROOT_URL}/team/${teamName}/matches?season=${season}`);
                const data = await response.json();
                setMatch(data);
            };
            fetchMatches()


        }, [teamName, season]
    )


    return (
        <div className="MatchPage">
            <div className="year-selector">
                <h4> Select Year </h4>
                <YearSelector teamName={teamName}/>
            </div>

            <div>
                <h1 className="page-heading">{teamName} matches for {season-1}/{season}</h1>
            {
                matches.map(match => <MatchDetailCard key={match.matchID} teamName = {teamName} match = {match}/>)
            }
            </div>
        </div>
    );
}

export default MatchPage;

