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
	
	private static int NUMBER_OF_WEEKS = 5;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<Team> teams = mainService.getAllTeams();
		
		//Collections.shuffle(teams);
		
		int size = teams.size();
		
		
		
		List<Game> firstHalf = new ArrayList<>();
		List<Game> secondHalf = new ArrayList<>();
		
		Random rand = new Random(System.currentTimeMillis());
		
		LocalDate dateFirst = LocalDate.of(2018, 3, 15);
		LocalDate dateSecond = dateFirst.plusWeeks(5);
		
		for (int i=0;i<NUMBER_OF_WEEKS; i++) {
			for (int k=0;k < size; k+=2) {
				Team team1 = teams.get((i+k) % size);
				Team team2 = teams.get((i+k+1) % size);
				
				Date date1 = Date.from(dateFirst.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
				Date date2 = Date.from(dateSecond.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
				
								
				Game game1 = new Game(0l, team1, team2, rand.nextInt(6), rand.nextInt(6),date1);
				game1.getHomeTeam().getGames().add(game1);
				game1.getGuestTeam().getGames().add(game1);
				
				Game game2 = new Game(0l, team2, team1, rand.nextInt(6), rand.nextInt(6),date2);
				
				
				firstHalf.add(game1);
				
				secondHalf.add(game2);
			}
		}
		
		/*firstHalf.forEach(game -> {
			
			Game savedGame = gameService.saveGame(game);
			mainService.saveTeam(savedGame.getHomeTeam());
			mainService.saveTeam(savedGame.getGuestTeam());
			
			System.out.println(savedGame.getHomeTeam().getGames().size());
		});
		
		 Optional<Team> team1 = mainService.getTeam(1l);
		 
		 if (team1.isPresent()) {
			 List<Game> games = team1.get().getGames();
			 for (Game tg : games) {
				System.out.println(tg.getDate());
			}
		 }*/
		
		

	}
	
	
	
	

}
