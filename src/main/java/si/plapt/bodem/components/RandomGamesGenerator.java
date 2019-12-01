package si.plapt.bodem.components;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;
import si.plapt.bodem.entities.Game;
import si.plapt.bodem.entities.Team;
import si.plapt.bodem.services.GameService;
import si.plapt.bodem.services.MainService;

@Log4j2
@Component
public class RandomGamesGenerator implements ApplicationRunner {
	
	@Autowired
	MainService mainService;
	
	@Autowired
	GameService gameService;
	

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<Team> teams = mainService.getAllTeams();
		
		int size = teams.size();
		
		int rounds = size - 1;
		
		int half = size / 2;
		
		List<Game> firstRounds = new ArrayList<>();
		List<Game> secondRounds = new ArrayList<>();
		
		Random rand = new Random(System.currentTimeMillis());
		
		for (int i=0; i< rounds; i++){
			int m = 0;
			int n = size - 1;
			
			LocalDate dateFirst = LocalDate.of(2018, 3, 15);
			LocalDate dateSecond = dateFirst.plusWeeks(rounds);
			Date date1 = Date.from(dateFirst.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
			Date date2 = Date.from(dateSecond.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
			
			for (int j = 0; j < half; j++) {
				Game game1 = new Game(0l, teams.get(m), teams.get(n), rand.nextInt(6), rand.nextInt(6),date1);
				firstRounds.add(game1);
				Game game2 = new Game(0l, teams.get(n), teams.get(m), rand.nextInt(6), rand.nextInt(6),date2);
				secondRounds.add(game2);
				m++;
				n--;
			}			
				
			Team tmp = teams.get(size -1);
			for (int j=size-1; j > 0; j--) {
				teams.set(j, teams.get(j-1));
			}
			teams.set(1,tmp);
			
			
		}
	
		for (Game game : firstRounds) {
			Game savedGame = gameService.saveGame(game);			
			System.out.println(savedGame.getId() + ":" + savedGame.getHomeTeam().getTitle() + ":" + savedGame.getGuestTeam().getTitle());
		}
		
		for (Game game : secondRounds) {
			Game savedGame = gameService.saveGame(game);			
			System.out.println(savedGame.getId() + ":" + savedGame.getHomeTeam().getTitle() + ":" + savedGame.getGuestTeam().getTitle());
		}
		
		
		
		
		

	}
	
	
	
	

}
