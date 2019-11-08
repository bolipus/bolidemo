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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import si.plapt.bodem.dtos.MemberDTO;
import si.plapt.bodem.dtos.TeamDTO;
import si.plapt.bodem.entities.Member;
import si.plapt.bodem.entities.Role;
import si.plapt.bodem.entities.Team;
import si.plapt.bodem.services.CodesService;
import si.plapt.bodem.services.MainService;

@RestController
@CrossOrigin(origins = {
	"http://localhost:4200",
	"https://bldemo.web.app"	
})

@RequestMapping("api/v1")
public class MainController {
	
	private static final String MEMBER_WITH_ID_S_NOT_FOUND = "Member with id %s not found";
	
	private static final String TEAM_WITH_ID_S_NOT_FOUND = "Team with id %s not found";
	
	
	@Autowired
	MainService mainService;
	
	@Autowired
	CodesService codeService;
	
	@GetMapping("/members")
	public ResponseEntity<List<MemberDTO>> getAllMembers() {

		List<MemberDTO> membersDTO = mainService.getAllMembers().stream().map(Member::createMemberDTO)
				.collect(Collectors.toList());

		return ResponseEntity.ok(membersDTO);
	}

	@GetMapping("/members/{id}")
	public ResponseEntity<MemberDTO> getMember(@PathVariable("id") Long id) {
		Optional<Member> member = mainService.getMember(id);

		if (member.isPresent()) {
			return ResponseEntity.ok(member.get().createMemberDTO());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(MEMBER_WITH_ID_S_NOT_FOUND, id));
		}
	}
	
	@PostMapping("/members")
	public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO) {
		
		Optional<Role> role = codeService.getRole(memberDTO.getRole().getId());
		
		Member member = new Member(memberDTO, role.orElseGet(null));
		Member savedMember =  mainService.saveMember(member);
		
		return ResponseEntity.ok(savedMember.createMemberDTO());
	}
	
	@PostMapping("/members/{id}")
	public ResponseEntity<MemberDTO> updateMember(@PathVariable("id") Long id, @RequestBody MemberDTO memberDTO) {
		Optional<Member> member = mainService.getMember(id);
		
		if (!member.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(MEMBER_WITH_ID_S_NOT_FOUND, id));
		}
		
		Optional<Role> role = codeService.getRole(memberDTO.getRole().getId());
		
		member.get().update(memberDTO, role.get());
		
		Member savedMember = mainService.saveMember(member.get());
		
		return ResponseEntity.ok(savedMember.createMemberDTO());
	}
	
	@DeleteMapping("/members/{id}")
	public ResponseEntity<Void> deleteMember(@PathVariable("id") Long id) {
		Optional<Member> member = mainService.getMember(id);
		
		if (!member.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(MEMBER_WITH_ID_S_NOT_FOUND, id));
		}
		
		mainService.deleteMember(id);
		
		return ResponseEntity.ok().build();
	}
	
	
	
	@GetMapping("/teams")
	public ResponseEntity<List<TeamDTO>> getAllTeams() {

		List<TeamDTO> teamsDTO = mainService.getAllTeams().stream().map(Team::createTeamDTO)
				.collect(Collectors.toList());

		return ResponseEntity.ok(teamsDTO);
	}

	@GetMapping("/teams/{id}")
	public ResponseEntity<TeamDTO> getTeam(@PathVariable("id") Long id) {
		Optional<Team> team = mainService.getTeam(id);

		if (team.isPresent()) {
			return ResponseEntity.ok(team.get().createTeamDTO());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(TEAM_WITH_ID_S_NOT_FOUND, id));
		}
	}
	
	@PostMapping("/teams")
	public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO teamDTO) {
		Team team = new Team(teamDTO);
		Team savedTeam =  mainService.saveTeam(team);
		
		return ResponseEntity.ok(savedTeam.createTeamDTO());
	}
	
	@PostMapping("/teams/{id}")
	public ResponseEntity<TeamDTO> updateTeam(@PathVariable("id") Long id, @RequestBody TeamDTO teamDTO) {
		Optional<Team> team = mainService.getTeam(id);
		
		if (!team.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(TEAM_WITH_ID_S_NOT_FOUND, id));
		}
		
		team.get().update(teamDTO);
		
		Team savedTeam = mainService.saveTeam(team.get());
		
		return ResponseEntity.ok(savedTeam.createTeamDTO());
	}
	
	@DeleteMapping("/teams/{id}")
	public ResponseEntity<Void> deleteTeam(@PathVariable("id") Long id) {
		Optional<Team> team = mainService.getTeam(id);
		
		if (!team.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(TEAM_WITH_ID_S_NOT_FOUND, id));
		}
		
		mainService.deleteTeam(id);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/teams/{id}/members")
	public ResponseEntity<List<MemberDTO>> getTeamMembers(@PathVariable("id") Long id) {
		Optional<Team> team = mainService.getTeam(id);

		if (team.isPresent()) {
			
			List<MemberDTO> members = team.get().getMembers().stream().map(Member::createMemberDTO).collect(Collectors.toList());
			
			return ResponseEntity.ok(members);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(TEAM_WITH_ID_S_NOT_FOUND, id));
		}
	}
	
	
	@PostMapping("/teams/{id}/members")
	public ResponseEntity<Void> addMemberToTeam(@PathVariable("id") Long id, @RequestParam("memberId") Long memberId){
		Optional<Team> team = mainService.getTeam(id);

		if (team.isPresent()) {
			
			Optional<Member> member = mainService.getMember(memberId);
			
			if (member.isPresent()) {
				team.get().addMember(member.get());
				mainService.saveTeam(team.get());
			}
			
			return ResponseEntity.ok().build();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(TEAM_WITH_ID_S_NOT_FOUND, id));
		}
	}
	
	@DeleteMapping("/teams/{id}/members/{memberId}")
	public ResponseEntity<Void> removeMemberFromTeam(@PathVariable("id") Long id, @PathVariable("memberId") Long memberId){
		Optional<Team> team = mainService.getTeam(id);

		if (team.isPresent()) {
			
			Optional<Member> member = mainService.getMember(memberId);
			
			if (member.isPresent()) {
				team.get().removeMember(member.get());
				mainService.saveTeam(team.get());
			}
			
			return ResponseEntity.ok().build();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(TEAM_WITH_ID_S_NOT_FOUND, id));
		}
	}

	
}
