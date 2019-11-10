package si.plapt.bodem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import si.plapt.bodem.entities.Game;
import si.plapt.bodem.entities.Team;

@Repository
public interface GameRepository  extends CrudRepository<Game, Long>{
	
	List<Game> getAllGamesByHomeTeam(Team homeTeam);
	
	List<Game> getAllGamesByGuestTeam(Team guestTeam);
	
	@Query("SELECT g FROM Game g WHERE g.homeTeam=:team OR g.guestTeam=:team")
	List<Game> getAllGamesByTeam(Team team);
	
	@Query("SELECT g FROM Game g "
			+ "WHERE (g.homeTeam=:team AND g.homeScore > g.guestScore)"
			+ "OR (g.guestTeam=:team AND g.guestScore > g.homeScore)")
	List<Game> getAllWinningGamesByTeam(Team team);
	
	@Query("SELECT g FROM Game g "
			+ "WHERE (g.homeTeam=:team AND g.homeScore < g.guestScore)"
			+ "OR (g.guestTeam=:team AND g.guestScore < g.homeScore)")
	List<Game> getAllLosingGamesByTeam(Team team);
	
	@Query("SELECT g FROM Game g "
			+ "WHERE (g.homeTeam=:team AND g.homeScore = g.guestScore)"
			+ "OR (g.guestTeam=:team AND g.guestScore = g.homeScore)")
	List<Game> getAllDrawGamesByTeam(Team team);
	
}
