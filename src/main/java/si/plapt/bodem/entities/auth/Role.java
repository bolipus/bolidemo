package si.plapt.bodem.entities.auth;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "xrole")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Role {

	@Id
	@SequenceGenerator(name = "xrole_id_seq", sequenceName = "xrole_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "xrole_id_seq")
	private Long id;

	private String name;

	private String description;

	@ManyToMany(mappedBy = "roles")
	private List<User> users;

}
