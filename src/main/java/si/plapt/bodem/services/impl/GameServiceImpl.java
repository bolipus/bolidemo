package si.plapt.bodem.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import si.plapt.bodem.entities.Game;
import si.plapt.bodem.repositories.GameRepository;
import si.plapt.bodem.services.GameService;

public class GameServiceImpl implements GameService {
	
	@Autowired
	GameRepository gameRepository;

	@Override
	public List<Game> getAllGames() {
		List<Game> results = new ArrayList<>();
		gameRepository.findAll().forEach(results::add);
		return results;
	}

	@Override
	public Optional<Game> getGame(Long id) {
		return gameRepository.findById(id);
	}

	@Override
	public Game saveGame(Game game) {
		return gameRepository.save(game);
	}

	@Override
	public void deleteGame(Long id) {
		gameRepository.deleteById(id);

	}

}
