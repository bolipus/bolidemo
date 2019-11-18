package si.plapt.bodem.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PositionDTO {
	
	private Long id;
	
	private String name;
	
	private String description;

}
