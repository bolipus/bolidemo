package si.plapt.bodem.services.impl;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;

import si.plapt.bodem.entities.Game;
import si.plapt.bodem.entities.Team;
import si.plapt.bodem.repositories.GameRepository;
import si.plapt.bodem.services.AppException;
import si.plapt.bodem.services.GameService;

@Service
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

	@Override
	public ByteArrayOutputStream generatePdfReport() throws AppException {
		Document document = new Document();
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		try {
			PdfWriter.getInstance(document, bos);		 
			document.open();
			Font font = FontFactory.getFont(FontFactory.COURIER, 24, BaseColor.BLACK);
			Chunk chunk = new Chunk("Hello BoliDemo", font);	
			
			document.add(chunk);
			Font font2 = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLUE);
		
			Chunk chunk2 = new Chunk("It is only a test report.", font2);
			document.add(chunk2);
			
		} catch (DocumentException e) {
			throw new AppException(e.getMessage(), e);
		}
		document.close();
		
		return bos;
	}
	

}
