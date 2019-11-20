package si.plapt.bodem.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import si.plapt.bodem.dtos.GameDTO;
import si.plapt.bodem.dtos.TeamDTO;

@Entity
@Table(name="Game")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "home_team_id")
	private Team homeTeam;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "guest_team_id")
	private Team guestTeam;
	
	private Integer homeScore;

	private Integer guestScore;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	public Game(GameDTO gameDTO, Team homeTeam, Team guestTeam) {
		update(gameDTO, homeTeam, guestTeam);
	}
	
	public void update(GameDTO gameDTO, Team homeTeam, Team guestTeam) {
		id = gameDTO.getId();
		homeScore = gameDTO.getHomeScore();
		guestScore = gameDTO.getGuestScore();
		date = gameDTO.getDate();
		this.homeTeam = homeTeam;
		this.guestTeam = guestTeam;
	}
	
	
	public GameDTO createGameDTO() {
		
		TeamDTO homeTeamDTO = null;
		if (homeTeam != null) {
			homeTeamDTO = homeTeam.createTeamDTO();
		}
		
		TeamDTO guestTeamDTO = null;
		if (homeTeam != null) {
			guestTeamDTO = guestTeam.createTeamDTO();
		}			
		
		return new GameDTO(id, homeTeamDTO, guestTeamDTO, homeScore, guestScore, date);		
	}

}
