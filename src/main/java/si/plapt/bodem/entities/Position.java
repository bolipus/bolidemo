package si.plapt.bodem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import si.plapt.bodem.dtos.PositionDTO;

@Entity
@Table(name="position")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Position {
	
	@Id
	@SequenceGenerator(name = "position_id_seq", sequenceName = "position_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "position_id_seq")
	private Long id;
	
	@Column(length = 100)
	private String name;
	
	@Column(length = 255)
	private String description;
	
	public Position(PositionDTO positionDTO) {
		update(positionDTO);
	}
	
	public void update(PositionDTO positionDTO) {
		this.id = positionDTO.getId();
		this.name = positionDTO.getName();
		this.description = positionDTO.getDescription(); 
	}
	
	public PositionDTO createPositionDTO() {
		return new PositionDTO(id, name, description);
	}
	
}
