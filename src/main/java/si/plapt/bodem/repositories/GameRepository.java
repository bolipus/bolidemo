package si.plapt.bodem.repositories;

import org.springframework.data.repository.CrudRepository;

import si.plapt.bodem.entities.Game;

public interface GameRepository  extends CrudRepository<Game, Long>{

}
