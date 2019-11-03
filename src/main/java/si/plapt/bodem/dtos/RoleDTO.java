package si.plapt.bodem.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleDTO {
	
	private Long id;
	
	private String title;
	
	private String description;

}
