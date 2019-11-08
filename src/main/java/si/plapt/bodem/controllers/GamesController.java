package si.plapt.bodem.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import si.plapt.bodem.dtos.GameDTO;
import si.plapt.bodem.dtos.TeamDTO;
import si.plapt.bodem.entities.Game;
import si.plapt.bodem.entities.Team;
import si.plapt.bodem.services.GameService;
import si.plapt.bodem.services.MainService;

@RestController
@CrossOrigin(origins = {
	"http://localhost:4200",
	"https://bldemo.web.app"	
})
@RequestMapping("api/v1")
public class GamesController {
	
	private static final String GAME_WITH_ID_S_NOT_FOUND = "Game with id %s not found";
	
	@Autowired
	GameService gameService;
	
	@Autowired
	MainService mainService;

	@GetMapping("/games")
	public ResponseEntity<List<GameDTO>> getAllGames() {

		List<GameDTO> gamesDTO = gameService.getAllGames().stream().map(Game::createGameDTO)
				.collect(Collectors.toList());

		return ResponseEntity.ok(gamesDTO);
	}

	@GetMapping("/games/{id}")
	public ResponseEntity<GameDTO> getGame(@PathVariable("id") Long id) {
		Optional<Game> game = gameService.getGame(id);

		if (game.isPresent()) {
			return ResponseEntity.ok(game.get().createGameDTO());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(GAME_WITH_ID_S_NOT_FOUND, id));
		}
	}
	
	@PostMapping("/games")
	public ResponseEntity<GameDTO> createGame(@RequestBody GameDTO gameDTO) {
		
		Optional<Team> homeTeam = mainService.getTeam(gameDTO.getHomeTeam().getId());
		
		Optional<Team> guestTeam = mainService.getTeam(gameDTO.getHomeTeam().getId());
		
		Game game = new Game(gameDTO, homeTeam.orElseGet(null), guestTeam.orElseGet(null));
		
		Game savedGame =  gameService.saveGame(game);
		
		return ResponseEntity.ok(savedGame.createGameDTO());
	}
	
	@PostMapping("/games/{id}")
	public ResponseEntity<GameDTO> updateGame(@PathVariable("id") Long id, @RequestBody GameDTO gameDTO) {
		Optional<Game> game = gameService.getGame(id);
		
		if (!game.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(GAME_WITH_ID_S_NOT_FOUND, id));
		}
		
		Optional<Team> homeTeam = mainService.getTeam(gameDTO.getHomeTeam().getId());
		
		Optional<Team> guestTeam = mainService.getTeam(gameDTO.getHomeTeam().getId());
		
		
		game.get().update(gameDTO, homeTeam.orElseGet(null),guestTeam.orElseGet(null));
		
		Game savedGame = gameService.saveGame(game.get());
		
		return ResponseEntity.ok(savedGame.createGameDTO());
	}
	
	@DeleteMapping("/games/{id}")
	public ResponseEntity<Void> deleteGame(@PathVariable("id") Long id) {
		Optional<Game> game = gameService.getGame(id);
		
		if (!game.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(GAME_WITH_ID_S_NOT_FOUND, id));
		}
		
		gameService.deleteGame(id);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("teams/{id}/games")
	public ResponseEntity<List<GameDTO>> getAllGamesForTeam(@PathVariable("id") Long id) {
		
		Optional<Team> team = mainService.getTeam(id);

		if (team.isPresent()) {
			List<GameDTO> gamesDTO = gameService.getAllGamesForTeam(team.get()).stream().map(Game::createGameDTO)
					.collect(Collectors.toList());
			
			return ResponseEntity.ok(gamesDTO);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(GAME_WITH_ID_S_NOT_FOUND, id));
		}

	}
	
	@GetMapping("teams/{id}/home/games")
	public ResponseEntity<List<GameDTO>> getAllHomeGamesForTeam(@PathVariable("id") Long id) {
		
		Optional<Team> team = mainService.getTeam(id);

		if (team.isPresent()) {
			List<GameDTO> gamesDTO = gameService.getAllHomeGamesForTeam(team.get()).stream().map(Game::createGameDTO)
					.collect(Collectors.toList());
			
			return ResponseEntity.ok(gamesDTO);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(GAME_WITH_ID_S_NOT_FOUND, id));
		}

	}
	
	@GetMapping("teams/{id}/guest/games")
	public ResponseEntity<List<GameDTO>> getAllGuestGamesForTeam(@PathVariable("id") Long id) {
		
		Optional<Team> team = mainService.getTeam(id);

		if (team.isPresent()) {
			List<GameDTO> gamesDTO = gameService.getAllGuestGamesForTeam(team.get()).stream().map(Game::createGameDTO)
					.collect(Collectors.toList());
			
			return ResponseEntity.ok(gamesDTO);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(GAME_WITH_ID_S_NOT_FOUND, id));
		}

	}
	
	
	@GetMapping("teams/{id}/totalScore")
	public ResponseEntity<Integer> getTotalScoreForTeam(@PathVariable("id") Long id) {
		
		Optional<Team> team = mainService.getTeam(id);

		if (team.isPresent()) {
			Integer totalScore = gameService.getTotalScoreForTeam(team.get());
			
			return ResponseEntity.ok(totalScore);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(GAME_WITH_ID_S_NOT_FOUND, id));
		}

	}
	
	@GetMapping("teams/{id}/winning/games")
	public ResponseEntity<List<GameDTO>> getAllWinningGamesForTeam(@PathVariable("id") Long id) {
		
		Optional<Team> team = mainService.getTeam(id);

		if (team.isPresent()) {
			List<GameDTO> gamesDTO = gameService.getAllWinningGamesForTeam(team.get()).stream().map(Game::createGameDTO)
					.collect(Collectors.toList());
			
			return ResponseEntity.ok(gamesDTO);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(GAME_WITH_ID_S_NOT_FOUND, id));
		}
	}
	
	@GetMapping("teams/{id}/losing/games")
	public ResponseEntity<List<GameDTO>> getAllLosingGamesForTeam(@PathVariable("id") Long id) {
		
		Optional<Team> team = mainService.getTeam(id);

		if (team.isPresent()) {
			List<GameDTO> gamesDTO = gameService.getAllLosingGamesForTeam(team.get()).stream().map(Game::createGameDTO)
					.collect(Collectors.toList());
			
			return ResponseEntity.ok(gamesDTO);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(GAME_WITH_ID_S_NOT_FOUND, id));
		}

	}
	
	@GetMapping("teams/{id}/draw/games")
	public ResponseEntity<List<GameDTO>> getAllDrawGamesForTeam(@PathVariable("id") Long id) {
		
		Optional<Team> team = mainService.getTeam(id);

		if (team.isPresent()) {
			List<GameDTO> gamesDTO = gameService.getAllDrawGamesForTeam(team.get()).stream().map(Game::createGameDTO)
					.collect(Collectors.toList());
			
			return ResponseEntity.ok(gamesDTO);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(GAME_WITH_ID_S_NOT_FOUND, id));
		}

	}
}
