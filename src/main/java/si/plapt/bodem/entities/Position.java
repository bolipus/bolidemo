package si.plapt.bodem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import si.plapt.bodem.dtos.PositionDTO;

@Entity
@Table(name="Position")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Position {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
