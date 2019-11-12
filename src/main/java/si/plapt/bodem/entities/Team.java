package si.plapt.bodem.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import si.plapt.bodem.dtos.TeamDTO;

@Entity
@Table(name="Team")
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100)
	private String title;
	
	@Column(length = 255)
	private String description;
	
	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
	private List<Member> members;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Game> games;
	
	public Team(TeamDTO teamDTO) {
		update(teamDTO);
	}
	
	public void update(TeamDTO teamDTO) {
		id = teamDTO.getId();
		title = teamDTO.getTitle();
		description = teamDTO.getDescription();		
	}
	
	public void addMember(Member member) {
		member.setTeam(this);
		members.add(member);
	}
	
	public void removeMember(Member member) {
		member.setTeam(null);
		members.remove(member);
	}
	
	
	
	public TeamDTO createTeamDTO() {
		return new TeamDTO(id, title, description);
	}

	

	
}
