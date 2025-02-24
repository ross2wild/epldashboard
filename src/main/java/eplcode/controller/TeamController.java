package eplcode.controller;

import eplcode.data.JobCompletionNotificationListener;
import eplcode.model.Match;
import eplcode.model.Team;
import eplcode.repository.MatchRepository;
import eplcode.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
public class TeamController {

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/all-time-table")
    public List<Team> getAllTimeTable(){
        return teamRepository.findByOrderByTotalPointsDesc();
    }

    @GetMapping("/team")
    public Iterable<Team> getAllTeams() {
        return this.teamRepository.findAll();
    }


    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        Team team = this.teamRepository.findByTeamName(teamName);
        team.setMatches(matchRepository.findLatestMatchesByTeam(teamName, 5));

        return team;
    }


    @GetMapping("team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam String season) {
        return this.matchRepository.getByHomeTeamAndSeasonOrAwayTeamAndSeasonOrderByDateDesc(teamName, season, teamName, season);
    }




}
