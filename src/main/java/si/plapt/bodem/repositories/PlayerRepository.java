package si.plapt.bodem.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import si.plapt.bodem.entities.Player;
import si.plapt.bodem.entities.Position;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
	 List<Player> findAllPlayersByOrderByBirthdayAsc();
	 List<Player> findAllPlayerByPosition(Position position);	 
}
