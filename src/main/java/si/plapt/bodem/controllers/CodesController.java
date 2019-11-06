package si.plapt.bodem.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.log4j.Log4j2;
import si.plapt.bodem.dtos.RoleDTO;
import si.plapt.bodem.entities.Role;
import si.plapt.bodem.services.CodesService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "https://bldemo.web.app" })
@RequestMapping("api/v1/codes")
public class CodesController {

	@Autowired
	CodesService codeService;

	@GetMapping("/roles")
	public ResponseEntity<List<RoleDTO>> getAllRoles() {

		List<RoleDTO> rolesDTO = codeService.getAllRoles().stream().map(Role::createRoleDTO)
				.collect(Collectors.toList());

		return ResponseEntity.ok(rolesDTO);
	}

	@GetMapping("/roles/{id}")
	public ResponseEntity<RoleDTO> getRole(@PathVariable("id") Long id) {
		Optional<Role> role = codeService.getRole(id);

		if (role.isPresent()) {
			return ResponseEntity.ok(role.get().createRoleDTO());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role with id %s nor found", id));
		}
	}
	
	@PostMapping("/roles")
	public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
		Role role = new Role(roleDTO);
		Role savedRole =  codeService.saveRole(role);
		
		return ResponseEntity.ok(savedRole.createRoleDTO());
	}
	
	@PostMapping("/roles/{id}")
	public ResponseEntity<RoleDTO> updateRole(@PathVariable("id") Long id, @RequestBody RoleDTO roleDTO) {
		Optional<Role> role = codeService.getRole(id);
		
		if (!role.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role with id %s nor found", id));
		}
		
		role.get().updateRole(roleDTO);
		
		Role savedRole = codeService.saveRole(role.get());
		
		return ResponseEntity.ok(savedRole.createRoleDTO());
	}
	
	@DeleteMapping("/roles/{id}")
	public ResponseEntity<Void> deleteRole(@PathVariable("id") Long id) {
		Optional<Role> role = codeService.getRole(id);
		
		if (!role.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role with id %s nor found", id));
		}
		
		codeService.deleteRole(id);
		
		return ResponseEntity.ok().build();
	}

}
