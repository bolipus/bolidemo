package si.plapt.bodem.services;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import si.plapt.bodem.entities.Role;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CodeServiceTests {
	
	@Autowired
	private CodesService codeService;
	
	// ALL TEST CREATED ACCORDING TO INITAL DATABASE SETUP
	
	@Test
	public void fetchAllRolesTest() {
		List<Role> roles = codeService.getAllRoles();		
		assertTrue(roles.size() > 0);
	}
	
	@Test
	public void fetchRoleTest() {
		Optional<Role> roles = codeService.getRole(1l);
		assertTrue(roles.isPresent());
	}

	
	
	@Test
	public void addAndUpdateRoleTest() {
		Role role = new Role();
		role.setTitle("TestRole");
		role.setDescription("TestDescription");
		
		Role savedRole = codeService.saveRole(role);
		
		assertEquals(role.getTitle(), savedRole.getTitle());
		assertEquals(role.getDescription(), savedRole.getDescription());
		
		savedRole.setTitle("UpdatedRole");
		
		Role updatedRole = codeService.saveRole(role);
		
		assertEquals(savedRole.getId(), updatedRole.getId());
		assertEquals(savedRole.getTitle(), updatedRole.getTitle());
		assertNotEquals(role.getTitle(), updatedRole.getTitle());
	}
	
	@Test
	public void deleteRoleTest() {
		Role role = new Role();
		role.setTitle("TestRole");
		role.setDescription("TestDescription");
		
		Role savedRole = codeService.saveRole(role);
		
		assertEquals(role.getTitle(), savedRole.getTitle());
		assertEquals(role.getDescription(), savedRole.getDescription());
	
		codeService.deleteRole(role.getId());
		
		Optional<Role> deleted = codeService.getRole(role.getId());
		
		assertTrue(deleted.isPresent());
		
	}
	
	
}
