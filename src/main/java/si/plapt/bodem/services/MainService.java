package si.plapt.bodem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import si.plapt.bodem.entities.Member;
import si.plapt.bodem.entities.Team;

@Service
public interface MainService {

	List<Member> getAllMembers();
	
	Optional<Member> getMember(Long id);
	
	Member saveMember(Member member);
	
	void deleteMember(Long id);
	
	
	List<Team> getAllTeam();
	
	Optional<Team> getTeam(Long id);
	
	Team saveTeam(Team team);
	
	void deleteTeam(Long id);
	
	
	
	
	
	
}
