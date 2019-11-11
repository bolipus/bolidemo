package si.plapt.bodem.services;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import si.plapt.bodem.entities.Member;
import si.plapt.bodem.repositories.MemberRepository;
import si.plapt.bodem.repositories.RoleRepository;
import si.plapt.bodem.repositories.TeamRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MainServiceTests {
	
	@MockBean
	private MemberRepository memberRepository;
	
	@MockBean
	private TeamRepository teamRepository;

	@Autowired
	private MainService mainService;
	
	@Test
	public void getAllMembersTest() {
		Member member1 = new Member();
		member1.setId(1l);
		member1.setFirstName("Janez");
		member1.setLastName("Kranjski");
		
		List<Member> members = new ArrayList<>();
		members.add(member1);
		
		when(memberRepository.findAll()).thenReturn(members);
		
		List<Member> actualMembers = mainService.getAllMembers();
		
		assertTrue(actualMembers.size() ==  members.size());		
	}
}
