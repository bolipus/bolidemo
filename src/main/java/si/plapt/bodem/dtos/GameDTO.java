package si.plapt.bodem.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameDTO {
	
	private Long id;
	
	private TeamDTO homeTeam;
	
	private TeamDTO guestTeam;
	
	private Integer homeScore;

	private Integer guestScore;
}
