package si.plapt.bodem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import si.plapt.bodem.entities.Member;

@Service
public interface MainService {

	List<Member> getAllMembers();
	
	Optional<Member> getMember(Long id);
	
	Member saveMember(Member lake);
	
	void deleteMember(Long id);
	
}
