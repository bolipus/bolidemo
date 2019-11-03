package si.plapt.bodem.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import si.plapt.bodem.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	Iterable<Role> findAllRolesOrderByTitleAsc();
}
