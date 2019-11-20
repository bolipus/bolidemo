package si.plapt.bodem.entities;

import java.util.List;

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
	
	@OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
	private List<Player> players;
	
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Game> games;
	
	public Team(TeamDTO teamDTO) {
		update(teamDTO);
	}
	
	public void update(TeamDTO teamDTO) {
		id = teamDTO.getId();
		title = teamDTO.getTitle();
		description = teamDTO.getDescription();		
	}
	
	public void addPlayer(Player player) {
		player.setTeam(this);
		players.add(player);
	}
	
	
	public void removePlayer(Player player) {
		player.setTeam(null);
		players.remove(player);
	}
	
	
	public TeamDTO createTeamDTO() {
		return new TeamDTO(id, title, description);
	}

	

	
}
