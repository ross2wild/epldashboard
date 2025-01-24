import { React, useEffect, useState } from 'react';
import {useParams} from 'react-router-dom';
import {MatchDetailCard} from "../components/MatchDetailCard";
import {MatchSmallCard} from "../components/MatchSmallCard";


export const MatchPage = () => {


    const [matches, setMatch] = useState([]);
    const {teamName, season} = useParams();

    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch(`http://localhost:8080/team/${teamName}/matches?season=${season}`);
                const data = await response.json();
                setMatch(data);
            };
            fetchMatches()


        }, [teamName, season]
    )


    return (
        <div className="MatchPage">
            <h1>Match Page</h1>
            <h3>All Matches for {teamName} {season} season</h3>
            {
                matches.map(match => <MatchDetailCard teamName = {teamName} match = {match}/>)
            }
        </div>
    );
}

export default MatchPage;

