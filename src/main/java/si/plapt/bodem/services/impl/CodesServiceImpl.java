package si.plapt.bodem.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import si.plapt.bodem.entities.Role;
import si.plapt.bodem.repositories.RoleRepository;
import si.plapt.bodem.services.CodesService;

@Service
public class CodesServiceImpl implements CodesService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> getAllRoles() {
		return StreamSupport.stream(roleRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	@Override
	public List<Role> getAllRolesOrderByTitleDesc() {
		List<Role> results = new ArrayList<>();
		roleRepository.findAllRolesOrderByTitleAsc().forEach(results::add);		
		return results;
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
