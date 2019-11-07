package si.plapt.bodem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import si.plapt.bodem.dtos.RoleDTO;

@Entity
@Table(name="Role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100)
	private String title;
	
	@Column(length = 255)
	private String description;
	
	public Role(RoleDTO roleDTO) {
		update(roleDTO);
	}
	
	public void update(RoleDTO roleDTO) {
		this.id = roleDTO.getId();
		this.title = roleDTO.getTitle();
		this.description = roleDTO.getDescription(); 
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        return id != null && id.equals(((Role) o).getId());
    }
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	public RoleDTO createRoleDTO() {
		return new RoleDTO(id, title, description);
	}
	
}
