package eplcode.repository;

import eplcode.model.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TeamRepository extends CrudRepository<Team, Long> {

   Team findByTeamName(String teamName);

   List<Team> findByOrderByTotalPointsDesc();

}