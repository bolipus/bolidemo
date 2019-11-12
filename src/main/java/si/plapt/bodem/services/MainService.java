package si.plapt.bodem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import si.plapt.bodem.entities.Player;
import si.plapt.bodem.entities.Team;

@Service
public interface MainService {

	List<Player> getAllPlayers();
	
	Optional<Player> getPlayer(Long id);
	
	Player savePlayer(Player member);
	
	void deletePlayer(Long id);
	
	
	List<Team> getAllTeams();
	
	Optional<Team> getTeam(Long id);
	
	Team saveTeam(Team team);
	
	void deleteTeam(Long id);
	
	
	
	
	
	
}
