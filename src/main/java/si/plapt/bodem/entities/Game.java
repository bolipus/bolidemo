package si.plapt.bodem.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import si.plapt.bodem.dtos.GameDTO;
import si.plapt.bodem.dtos.TeamDTO;

@Entity
@Table(name = "game")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = { "homeTeam", "guestTeam" })
//@ToString(exclude= {"homeTeam", "guestTeam"})
public class Game {

	@Id
	@SequenceGenerator(name = "game_id_seq", sequenceName = "game_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_id_seq")
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JoinColumn(name = "home_team_id" )
	private Team homeTeam;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JoinColumn(name = "guest_team_id")
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
