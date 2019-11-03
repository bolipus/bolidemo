package si.plapt.bodem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import si.plapt.bodem.entities.Role;


@Service
public interface CodesService {
	
	List<Role> getAllRoles();
	
	List<Role> getAllRolesOrderByTitleDesc();
	
	Optional<Role> getRole(Long id);
	
	Role saveRole(Role role);
	
	void deleteRole(Long id);
}
