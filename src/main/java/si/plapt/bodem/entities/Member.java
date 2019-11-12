package si.plapt.bodem.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import si.plapt.bodem.dtos.MemberDTO;
import si.plapt.bodem.dtos.RoleDTO;

@Entity
@Table(name="Member")
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 255)
	private String firstName;
	
	@Column(length = 255)
	private String lastName;
	
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	@Column(length = 100)
	private String email;
	
	@Column(length = 16)
	private String phone;
	
	@OneToOne(optional = true)
	private Role role;

	@ManyToOne
	@JoinColumn(name="team_id", nullable=false)
	private Team team;
	
	public Member(MemberDTO memberDTO, Role role) {
		update(memberDTO, role);
	}
	
	public void update(MemberDTO memberDTO, Role role) {
		this.id = memberDTO.getId();
		this.firstName = memberDTO.getFirstName();
		this.lastName = memberDTO.getLastName();
		this.birthday = memberDTO.getBirthday();
		this.email = memberDTO.getEmail();
		this.phone = memberDTO.getPhone();
		this.role = role;
	}
	
	
	public MemberDTO createMemberDTO() {
		RoleDTO roleDTO = null;
		if (role!= null) {
			roleDTO = role.createRoleDTO();
		}
		
		return new MemberDTO(id, firstName, lastName, birthday, email, phone, roleDTO);
	}

	
}
