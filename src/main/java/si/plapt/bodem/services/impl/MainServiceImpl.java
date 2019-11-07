package si.plapt.bodem.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import si.plapt.bodem.entities.Member;
import si.plapt.bodem.entities.Team;
import si.plapt.bodem.repositories.MemberRepository;
import si.plapt.bodem.repositories.TeamRepository;
import si.plapt.bodem.services.MainService;

@Service
public class MainServiceImpl implements MainService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private TeamRepository teamRepository;

	@Override
	public List<Member> getAllMembers() {
		List<Member> results = new ArrayList<>();
		memberRepository.findAll().forEach(results::add);
		return results;
	}

	@Override
	public Optional<Member> getMember(Long id) {
		return memberRepository.findById(id);
	}

	@Override
	public Member saveMember(Member member) {
		return memberRepository.save(member);
	}

	@Override
	public void deleteMember(Long id) {
		memberRepository.deleteById(id);		
	}

	@Override
	public List<Team> getAllTeams() {
		List<Team> results = new ArrayList<>();
		teamRepository.findAll().forEach(results::add);
		return results;
	}

	@Override
	public Optional<Team> getTeam(Long id) {
		return teamRepository.findById(id);
	}

	@Override
	public Team saveTeam(Team team) {
		return teamRepository.save(team);
	}

	@Override
	public void deleteTeam(Long id) {
		teamRepository.deleteById(id);		
	}

}
