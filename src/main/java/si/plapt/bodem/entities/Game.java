package si.plapt.bodem.entities;

import java.util.Date;

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
import lombok.NoArgsConstructor;
import si.plapt.bodem.dtos.GameDTO;
import si.plapt.bodem.dtos.TeamDTO;

@Entity
@Table(name="Game")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "home_team_id")
	private Team homeTeam;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "guest_team_id")
	private Team guestTeam;
	
	private Integer homeScore;

	private Integer guestScore;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        return id != null && id.equals(((Game) o).getId());
    }
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	public GameDTO createGameDTO() {
		TeamDTO homeTeamDTO = homeTeam.createTeamDTO();
		TeamDTO guestTeamDTO = guestTeam.createTeamDTO();
		
		return new GameDTO(id, homeTeamDTO, guestTeamDTO, homeScore, guestScore);		
	}

}
