package si.plapt.bodem.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import si.plapt.bodem.entities.auth.Role;
import si.plapt.bodem.entities.auth.User;
import si.plapt.bodem.repositories.auth.RoleRepository;
import si.plapt.bodem.repositories.auth.UserRepository;
import si.plapt.bodem.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	@Override
	public Optional<User> getUser(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);		
	}

	@Override
	public List<Role> getAllRoles() {
		List<Role> roles = new ArrayList<>();
		roleRepository.findAll().forEach(roles::add);
		return roles;
	}

	@Override
	public Optional<Role> getRole(Long id) {
		return roleRepository.findById(id);
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void deleteRole(Long id) {
		roleRepository.deleteById(id);		
	}

}
