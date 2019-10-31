package si.plapt.bodem.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamDTO {
	
	private Long id;
	
	private String title;
	
	private String description;

}
