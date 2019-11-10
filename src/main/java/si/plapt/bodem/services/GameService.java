package si.plapt.bodem.services;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import si.plapt.bodem.entities.Game;
import si.plapt.bodem.entities.Team;

@Service
public interface GameService {

	List<Game> getAllGames();
	
	Optional<Game> getGame(Long id);
	
	Game saveGame(Game game);
	
	void deleteGame(Long id);
	
	List<Game> getAllGamesForTeam(Team team);
	
	List<Game> getAllHomeGamesForTeam(Team team);
	
	List<Game> getAllGuestGamesForTeam(Team team);
	
	List<Game> getAllWinningGamesForTeam(Team team);
	
	List<Game> getAllLosingGamesForTeam(Team team);
	
	List<Game> getAllDrawGamesForTeam(Team team);
	
	Integer getTotalScoreForTeam(Team team);
	
	ByteArrayOutputStream generatePdfReport() throws AppException;
	
	
	
}
