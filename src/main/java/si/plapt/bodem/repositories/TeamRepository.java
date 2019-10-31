package si.plapt.bodem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import si.plapt.bodem.entities.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

}
