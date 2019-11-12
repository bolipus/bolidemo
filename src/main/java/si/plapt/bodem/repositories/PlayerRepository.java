package si.plapt.bodem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import si.plapt.bodem.entities.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

}
