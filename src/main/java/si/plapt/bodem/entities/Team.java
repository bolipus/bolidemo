package si.plapt.bodem.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.GroupDefinitionException;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import si.plapt.bodem.dtos.TeamDTO;

@Entity
@Table(name="Team")
@Data
@RequiredArgsConstructor
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String description;
	
	@ManyToMany(mappedBy = "teams")
	private List<Member> members;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        return id != null && id.equals(((Team) o).getId());
    }
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	public TeamDTO createTeamDTO() {
		return new TeamDTO(id, title, description);
	}

	
}
