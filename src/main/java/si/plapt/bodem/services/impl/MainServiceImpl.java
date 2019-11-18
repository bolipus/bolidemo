package si.plapt.bodem.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import si.plapt.bodem.entities.Player;
import si.plapt.bodem.entities.Position;
import si.plapt.bodem.entities.Team;
import si.plapt.bodem.repositories.PlayerRepository;
import si.plapt.bodem.repositories.TeamRepository;
import si.plapt.bodem.services.AppException;
import si.plapt.bodem.services.CodesService;
import si.plapt.bodem.services.MainService;

@Service
public class MainServiceImpl implements MainService {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private CodesService codeService;


	@Override
	public List<Player> getAllPlayers() {
		List<Player> players = new ArrayList<>();
		playerRepository.findAll().forEach(players::add);
		return players;
	}

	@Override
	public Optional<Player> getPlayer(Long id) {
		return playerRepository.findById(id);
	}

	@Override
	@Transactional
	public Player savePlayer(Player member) {
		return playerRepository.save(member);
	}

	@Override
	@Transactional
	public void deletePlayer(Long id) {
		playerRepository.deleteById(id);		
	}

	@Override
	public List<Team> getAllTeams() {
		List<Team> teams = new ArrayList<>();
		teamRepository.findAll().forEach(teams::add);
		return teams;
	}

	@Override
	public Optional<Team> getTeam(Long id) {
		return teamRepository.findById(id);
	}

	@Override
	public Team saveTeam(Team team) {
		return teamRepository.save(team);
	}

	@Override
	public void deleteTeam(Long id) {
		teamRepository.deleteById(id);		
	}

	@Override
	public List<Player> getAllPlayersOrderByBirthDay() {
		return playerRepository.findlPlayersByOrderByBirthdayAsc();
	}

	@Override
	public List<Player> getAllPlayersByPosition(Long positionId) throws AppException {
		Optional<Position> position = codeService.getPosition(positionId);
		
		if (position.isPresent()) {
			return playerRepository.findAllPlayerByPosition(position.get());
		} else {
			throw new AppException(String.format("Position with id %s not found", positionId));
		}
		
		
	}

}
