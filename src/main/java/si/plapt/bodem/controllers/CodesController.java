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

import si.plapt.bodem.dtos.PositionDTO;
import si.plapt.bodem.entities.Position;
import si.plapt.bodem.services.CodesService;

@RestController
@CrossOrigin(origins = { 
		"http://localhost:4200",
		"https://bldemo.web.app" 
})
@RequestMapping("api/v1/codes")
public class CodesController {

	private static final String POSITION_WITH_ID_S_NOT_FOUND = "Position with id %s not found";
	
	@Autowired
	CodesService codeService;

	@GetMapping("/positions")
	public ResponseEntity<List<PositionDTO>> getAllPosition() {

		List<PositionDTO> rolesDTO = codeService.getAllPositions().stream().map(Position::createPositionDTO)
				.collect(Collectors.toList());

		return ResponseEntity.ok(rolesDTO);
	}

	@GetMapping("/positions/{id}")
	public ResponseEntity<PositionDTO> getPosition(@PathVariable("id") Long id) {
		Optional<Position> position = codeService.getPosition(id);

		if (position.isPresent()) {
			return ResponseEntity.ok(position.get().createPositionDTO());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(POSITION_WITH_ID_S_NOT_FOUND, id));
		}
	}
	
	@PostMapping("/positions")
	public ResponseEntity<PositionDTO> createPosition(@RequestBody PositionDTO positionDTO) {
		Position position = new Position(positionDTO);
		Position savedPosition =  codeService.savePosition(position);
		
		return ResponseEntity.ok(savedPosition.createPositionDTO());
	}
	
	@PostMapping("/positions/{id}")
	public ResponseEntity<PositionDTO> updatePosition(@PathVariable("id") Long id, @RequestBody PositionDTO positionDTO) {
		Optional<Position> position = codeService.getPosition(id);
		
		if (!position.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(POSITION_WITH_ID_S_NOT_FOUND, id));
		}
		
		position.get().update(positionDTO);
		
		Position savedPosition = codeService.savePosition(position.get());
		
		return ResponseEntity.ok(savedPosition.createPositionDTO());
	}
	
	@DeleteMapping("/positions/{id}")
	public ResponseEntity<Void> deletePosition(@PathVariable("id") Long id) {
		Optional<Position> position = codeService.getPosition(id);
		
		if (!position.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(POSITION_WITH_ID_S_NOT_FOUND, id));
		}
		
		codeService.deletePosition(id);
		
		return ResponseEntity.ok().build();
	}

}
