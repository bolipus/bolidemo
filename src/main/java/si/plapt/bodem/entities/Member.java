package si.plapt.bodem.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import si.plapt.bodem.dtos.MemberDTO;

@Entity
@Table(name="Member")
@Data
@RequiredArgsConstructor
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private Date birthday;
	
	private String email;
	
	private String phone;

	@ManyToMany
	@JoinTable(name = "member_team",
		joinColumns = @JoinColumn(name="member_id"),
		inverseJoinColumns = @JoinColumn(name="team_id")
	)
	private List<Team> teams;
	
	public void addTeam(Team team) {
		teams.add(team);
		team.getMembers().add(this);
	}
	
	public void removeTeam(Team team) {
		teams.remove(team);
		team.getMembers().remove(this);
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        return id != null && id.equals(((Member) o).getId());
    }
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	public MemberDTO createMemberDTO() {
		return new MemberDTO(id, firstName, lastName, birthday, email, phone);
	}
	
}
