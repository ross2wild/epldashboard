package eplcode.repository;

import eplcode.model.Team;
import org.springframework.data.repository.CrudRepository;


public interface TeamRepository extends CrudRepository<Team, Long> {

   Team findByTeamName(String teamName);
}