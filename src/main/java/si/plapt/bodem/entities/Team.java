package si.plapt.bodem.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.GroupDefinitionException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import si.plapt.bodem.dtos.TeamDTO;

@Entity
@Table(name="Team")
@Data
@NoArgsConstructor
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String description;
	
	@ManyToMany(mappedBy = "teams", fetch = FetchType.LAZY)
	private List<Member> members;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Game> games;
	
	public Team(TeamDTO teamDTO) {
		id = teamDTO.getId();
		title = teamDTO.getTitle();
		description = teamDTO.getDescription();		
	}
	
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
