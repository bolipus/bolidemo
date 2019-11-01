package si.plapt.bodem.dtos;

import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import si.plapt.bodem.entities.Team;

@Data
@AllArgsConstructor
public class GameDTO {
	
	private Long id;
	
	private TeamDTO homeTeam;
	
	private TeamDTO guestTeam;
	
	private Integer homeScore;

	private Integer guestScore;
}
