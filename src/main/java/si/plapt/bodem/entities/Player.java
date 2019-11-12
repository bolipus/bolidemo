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
import si.plapt.bodem.dtos.PlayerDTO;
import si.plapt.bodem.dtos.PositionDTO;

@Entity
@Table(name="Player")
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Player {

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
	private Position position;

	@ManyToOne
	@JoinColumn(name="team_id", nullable=false)
	private Team team;
	
	public Player(PlayerDTO playerDTO, Position position) {
		update(playerDTO, position);
	}
	
	public void update(PlayerDTO memberDTO, Position position) {
		this.id = memberDTO.getId();
		this.firstName = memberDTO.getFirstName();
		this.lastName = memberDTO.getLastName();
		this.birthday = memberDTO.getBirthday();
		this.email = memberDTO.getEmail();
		this.phone = memberDTO.getPhone();
		this.position = position;
	}
	
	
	public PlayerDTO createPlayerDTO() {
		PositionDTO positionDTO = null;
		if (position!= null) {
			positionDTO = position.createPositionDTO();
		}
		
		return new PlayerDTO(id, firstName, lastName, birthday, email, phone, positionDTO);
	}

	
}
