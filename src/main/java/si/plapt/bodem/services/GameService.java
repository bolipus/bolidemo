package si.plapt.bodem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import si.plapt.bodem.entities.Game;

@Service
public interface GameService {

	List<Game> getAllGames();
	
	Optional<Game> getGame(Long id);
	
	Game saveGame(Game game);
	
	void deleteGame(Long id);
}
