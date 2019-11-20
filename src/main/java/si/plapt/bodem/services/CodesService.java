package si.plapt.bodem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import si.plapt.bodem.entities.Position;


@Service
public interface CodesService {
	
	List<Position> getAllPositions();
	
	List<Position> getAllPositionOrderByNameDesc();
	
	Optional<Position> getPosition(Long id);
	
	Position savePosition(Position role);
	
	void deletePosition(Long id);
}
