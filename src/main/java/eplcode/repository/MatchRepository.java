package eplcode.repository;

import eplcode.model.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MatchRepository extends CrudRepository<Match, Long> {


    List<Match> getByHomeTeamOrAwayTeamOrderByDateDesc(String teamName, String teamName2, Pageable pageable);

    List<Match> getByHomeTeamAndSeasonOrAwayTeamAndSeasonOrderByDateDesc(String homeTeam, String season1, String awayTeam, String season2);

    default List<Match> findLatestMatchesByTeam(String teamName, int count) {
        return getByHomeTeamOrAwayTeamOrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
    }



}
