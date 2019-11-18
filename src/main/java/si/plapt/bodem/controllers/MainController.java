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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import si.plapt.bodem.dtos.PlayerDTO;
import si.plapt.bodem.dtos.TeamDTO;
import si.plapt.bodem.entities.Player;
import si.plapt.bodem.entities.Position;
import si.plapt.bodem.entities.Team;
import si.plapt.bodem.services.AppException;
import si.plapt.bodem.services.CodesService;
import si.plapt.bodem.services.MainService;

@RestController
@CrossOrigin(origins = {
	"http://localhost:4200",
	"https://bldemo.web.app"	
})

@RequestMapping("api/v1")
public class MainController {
	
	private static final String PLAYER_WITH_ID_S_NOT_FOUND = "Player with id %s not found";
	
	private static final String TEAM_WITH_ID_S_NOT_FOUND = "Team with id %s not found";
	
	
	@Autowired
	MainService mainService;
	
	@Autowired
	CodesService codeService;
	
	@GetMapping("/players")
	public ResponseEntity<List<PlayerDTO>> getAllPlayers() {

		List<PlayerDTO> playersDTO = mainService.getAllPlayers().stream().map(Player::createPlayerDTO)
				.collect(Collectors.toList());

		return ResponseEntity.ok(playersDTO);
	}
	
	@GetMapping("/players/birthday")
	public ResponseEntity<List<PlayerDTO>> getAllPlayersOrderByBirthday() {

		List<PlayerDTO> playersDTO = mainService.getAllPlayersOrderByBirthDay().stream().map(Player::createPlayerDTO)
				.collect(Collectors.toList());

		return ResponseEntity.ok(playersDTO);
	}
	
	@GetMapping("/players/position/{positionId}")
	public ResponseEntity<List<PlayerDTO>> getAllPlayersByPosition(@PathVariable("positionId") Long positionId) {

		List<PlayerDTO> playersDTO;
		try {
			playersDTO = mainService.getAllPlayersByPosition(positionId).stream().map(Player::createPlayerDTO)
					.collect(Collectors.toList());
		} catch (AppException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

		return ResponseEntity.ok(playersDTO);
	}


	@GetMapping("/players/{id}")
	public ResponseEntity<PlayerDTO> getPlayer(@PathVariable("id") Long id) {
		Optional<Player> player = mainService.getPlayer(id);

		if (player.isPresent()) {
			return ResponseEntity.ok(player.get().createPlayerDTO());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(PLAYER_WITH_ID_S_NOT_FOUND, id));
		}
	}
	
	@PostMapping("/players")
	public ResponseEntity<PlayerDTO> createPlayer(@RequestBody PlayerDTO playerDTO) {
		
		Optional<Position> role = codeService.getPosition(playerDTO.getPosition().getId());
		
		Player player = new Player(playerDTO, role.orElseGet(null));
		Player savedPlayer =  mainService.savePlayer(player);
		
		return ResponseEntity.ok(savedPlayer.createPlayerDTO());
	}
	
	
	
	@PostMapping("/players/{id}")
	public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable("id") Long id, @RequestBody PlayerDTO playerDTO) {
		Optional<Player> player = mainService.getPlayer(id);
		
		if (!player.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(PLAYER_WITH_ID_S_NOT_FOUND, id));
		}
		
		Optional<Position> role = codeService.getPosition(playerDTO.getPosition().getId());
		
		player.get().update(playerDTO, role.get());
		
		Player savedPlayer = mainService.savePlayer(player.get());
		
		return ResponseEntity.ok(savedPlayer.createPlayerDTO());
	}
	
	@DeleteMapping("/players/{id}")
	public ResponseEntity<Void> deletePlayer(@PathVariable("id") Long id) {
		Optional<Player> player = mainService.getPlayer(id);
		
		if (!player.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(PLAYER_WITH_ID_S_NOT_FOUND, id));
		}
		
		mainService.deletePlayer(id);
		
		return ResponseEntity.ok().build();
	}
	
	
	
	@GetMapping("/teams")
	public ResponseEntity<List<TeamDTO>> getAllTeams() {

		List<TeamDTO> teamsDTO = mainService.getAllTeams().stream().map(Team::createTeamDTO)
				.collect(Collectors.toList());

		return ResponseEntity.ok(teamsDTO);
	}

	@GetMapping("/teams/{id}")
	public ResponseEntity<TeamDTO> getTeam(@PathVariable("id") Long id) {
		Optional<Team> team = mainService.getTeam(id);

		if (team.isPresent()) {
			return ResponseEntity.ok(team.get().createTeamDTO());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(TEAM_WITH_ID_S_NOT_FOUND, id));
		}
	}
	
	@PostMapping("/teams")
	public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO teamDTO) {
		Team team = new Team(teamDTO);
		Team savedTeam =  mainService.saveTeam(team);
		
		return ResponseEntity.ok(savedTeam.createTeamDTO());
	}
	
	@PostMapping("/teams/{id}")
	public ResponseEntity<TeamDTO> updateTeam(@PathVariable("id") Long id, @RequestBody TeamDTO teamDTO) {
		Optional<Team> team = mainService.getTeam(id);
		
		if (!team.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(TEAM_WITH_ID_S_NOT_FOUND, id));
		}
		
		team.get().update(teamDTO);
		
		Team savedTeam = mainService.saveTeam(team.get());
		
		return ResponseEntity.ok(savedTeam.createTeamDTO());
	}
	
	@DeleteMapping("/teams/{id}")
	public ResponseEntity<Void> deleteTeam(@PathVariable("id") Long id) {
		Optional<Team> team = mainService.getTeam(id);
		
		if (!team.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(TEAM_WITH_ID_S_NOT_FOUND, id));
		}
		
		mainService.deleteTeam(id);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/teams/{id}/players")
	public ResponseEntity<List<PlayerDTO>> getTeamPlayers(@PathVariable("id") Long id) {
		Optional<Team> team = mainService.getTeam(id);

		if (team.isPresent()) {
			
			List<PlayerDTO> players = team.get().getPlayers().stream().map(Player::createPlayerDTO).collect(Collectors.toList());
			
			return ResponseEntity.ok(players);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(TEAM_WITH_ID_S_NOT_FOUND, id));
		}
	}
	
	
	@PostMapping("/teams/{id}/players")
	public ResponseEntity<Void> addPlayerToTeam(@PathVariable("id") Long id, @RequestParam("playerId") Long playerId){
		Optional<Team> team = mainService.getTeam(id);

		if (team.isPresent()) {
			
			Optional<Player> player = mainService.getPlayer(playerId);
			
			if (player.isPresent()) {
				team.get().addPlayer(player.get());
				mainService.saveTeam(team.get());
			}
			
			return ResponseEntity.ok().build();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(TEAM_WITH_ID_S_NOT_FOUND, id));
		}
	}
	
	@DeleteMapping("/teams/{id}/players/{playerId}")
	public ResponseEntity<Void> removePlayerFromTeam(@PathVariable("id") Long id, @PathVariable("playerId") Long playerId){
		Optional<Team> team = mainService.getTeam(id);

		if (team.isPresent()) {
			
			Optional<Player> player = mainService.getPlayer(playerId);
			
			if (player.isPresent()) {
				team.get().removePlayer(player.get());
				mainService.saveTeam(team.get());
			}
			
			return ResponseEntity.ok().build();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(TEAM_WITH_ID_S_NOT_FOUND, id));
		}
	}

	
}
