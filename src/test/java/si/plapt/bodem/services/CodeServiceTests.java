package si.plapt.bodem.services;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import si.plapt.bodem.entities.Role;
import si.plapt.bodem.repositories.RoleRepository;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CodeServiceTests {
	
	@MockBean
	private RoleRepository roleRepository;
	
	@Autowired
	private CodesService codeService;
	
	
	@Test
	public void getAllRolesTest() {
		Role role = new Role(1l,"Test","Test");
		Role role2 = new Role(2l,"Test2","Test2");
		
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		roles.add(role2);
		
		when(roleRepository.findAll()).thenReturn(roles);
		
		List<Role> actualRoles = codeService.getAllRoles();	
		
		assertTrue(actualRoles.size() ==  roles.size());
		assertTrue(actualRoles.get(0).getId() ==  roles.get(0).getId());
	}
	
	@Test
	public void fetchRoleTest() {
		Role role = new Role(1l,"Test","Test");
		
		when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
		
		Optional<Role> actualRole = codeService.getRole(1l);
		
		assertTrue(actualRole.isPresent());
		
		assertTrue(role.getId() == actualRole.get().getId());
		
		assertEquals(role.getDescription(), actualRole.get().getDescription());
	}

	
	
	@Test
	public void addAndUpdateRoleTest() {
		Role role = new Role(1l,"Test","Test");
		
		Role savedRole = new Role(1l,"Test","Test");
		
		Role updatedRole = new Role(1l,"Updated","Test");
		
		when(roleRepository.save(role))
			.thenReturn(savedRole)
			.thenReturn(updatedRole);
		
		
		savedRole = codeService.saveRole(role);
		
		assertEquals(role.getTitle(), savedRole.getTitle());
		assertEquals(role.getDescription(), savedRole.getDescription());
		
		savedRole.setTitle("Updated");
		
		updatedRole = codeService.saveRole(role);
		
		assertEquals(savedRole.getId(), updatedRole.getId());
		assertEquals(savedRole.getTitle(), updatedRole.getTitle());
		assertNotEquals(role.getTitle(), updatedRole.getTitle());
	}
	
	@Test
	public void deleteRoleTest() {
		
		Role role = new Role(1l,"Test","Test");
		
		when(roleRepository.findById(1L)).thenReturn(Optional.of(role)).thenReturn(Optional.empty());
		
		Optional<Role> actualRole = codeService.getRole(1l);
		
		assertTrue(actualRole.isPresent());
	
		codeService.deleteRole(role.getId());
		
		Optional<Role> deleted = codeService.getRole(role.getId());
		
		assertTrue(!deleted.isPresent());
		
	}
	
	
}
