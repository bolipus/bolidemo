package si.plapt.bodem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import si.plapt.bodem.entities.auth.Role;
import si.plapt.bodem.entities.auth.User;

@Service
public interface UserService {

	List<User> getAllUsers();
	
	Optional<User> getUser(Long id);
	
	Optional<User> getByUsername(String username);
	
	User saveUser(User user);
	
	void deleteUser(Long id);
	
	
	List<Role> getAllRoles();
	
	Optional<Role> getRole(Long id);
	
	Role saveRole(Role role);
	
	void deleteRole(Long id);
}
