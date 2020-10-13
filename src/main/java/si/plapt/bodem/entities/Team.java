package si.plapt.bodem.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import si.plapt.bodem.dtos.TeamDTO;

@Entity
@Table(name="team")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude= {"homeGames", "guestGames", })
@ToString(exclude= {"homeGames", "guestGames", "players"})
public class Team {
	
	@Id
	@SequenceGenerator(name = "team_id_seq", sequenceName = "team_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_id_seq")
	private Long id;
	
	@Column(length = 100)
	private String title;
	
	@Column(length = 255)
	private String description;
	
	@OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
	private List<Player> players;
	
	@OneToMany(mappedBy = "homeTeam", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Game> homeGames;
	
	@OneToMany(mappedBy = "guestTeam", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Game> guestGames;
	
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
