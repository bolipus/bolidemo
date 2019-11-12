package si.plapt.bodem.services;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import si.plapt.bodem.entities.Player;
import si.plapt.bodem.repositories.PlayerRepository;
import si.plapt.bodem.repositories.TeamRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MainServiceTests {
	
	@MockBean
	private PlayerRepository playerRepository;
	
	@MockBean
	private TeamRepository teamRepository;

	@Autowired
	private MainService mainService;
	
	@Test
	public void getAllPlayersTest() {
		Player player = new Player();
		player.setId(1l);
		player.setFirstName("Janez");
		player.setLastName("Kranjski");
		
		List<Player> players = new ArrayList<>();
		players.add(player);
		
		when(playerRepository.findAll()).thenReturn(players);
		
		List<Player> actualPlayers = mainService.getAllPlayers();
		
		assertTrue(actualPlayers.size() ==  players.size());		
	}
}
