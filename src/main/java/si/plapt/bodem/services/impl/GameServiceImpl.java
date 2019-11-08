package si.plapt.bodem.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import si.plapt.bodem.entities.Game;
import si.plapt.bodem.entities.Team;
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
	@Transactional
	public Game saveGame(Game game) {
		return gameRepository.save(game);
	}

	@Override
	@Transactional
	public void deleteGame(Long id) {
		gameRepository.deleteById(id);
	}

	@Override
	public List<Game> getAllGamesForTeam(Team team) {
		return gameRepository.getAllGamesByTeam(team);
	}

	@Override
	public List<Game> getAllHomeGamesForTeam(Team team) {
		return gameRepository.getAllGamesByHomeTeam(team);
	}

	@Override
	public List<Game> getAllGuestGamesForTeam(Team team) {
		return gameRepository.getAllGamesByGuestTeam(team);
	}

	@Override
	public List<Game> getAllWinningGamesForTeam(Team team) {
		return gameRepository.getAllWinningGamesByTeam(team);
	}

	@Override
	public List<Game> getAllLosingGamesForTeam(Team team) {
		return gameRepository.getAllLosingGamesByTeam(team);
	}

	@Override
	public List<Game> getAllDrawGamesForTeam(Team team) {
		return getAllDrawGamesForTeam(team);
	}

	@Override
	public Integer getTotalScoreForTeam(Team team) {
		List<Game> games = getAllHomeGamesForTeam(team);
		
		Integer totalScore = games.stream()
				.mapToInt(Game::getHomeScore)
				.sum();
		
		games = getAllGuestGamesForTeam(team);

		totalScore += games.stream()
				.map(Game::getGuestScore)
				.collect(Collectors.summingInt(Integer::intValue));
		
		return totalScore;		
	}
	

}
