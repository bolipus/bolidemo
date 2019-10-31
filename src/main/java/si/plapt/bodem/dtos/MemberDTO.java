package si.plapt.bodem.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDTO {
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private Date birthday;
	
	private String email;
	
	private String phone;

}
