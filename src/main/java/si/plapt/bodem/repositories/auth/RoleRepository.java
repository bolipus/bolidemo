package si.plapt.bodem.repositories.auth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import si.plapt.bodem.entities.auth.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	
}
