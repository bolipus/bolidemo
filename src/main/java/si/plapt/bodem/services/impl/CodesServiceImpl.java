package si.plapt.bodem.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import si.plapt.bodem.entities.Position;
import si.plapt.bodem.repositories.PositionRepository;
import si.plapt.bodem.services.CodesService;

@Service
public class CodesServiceImpl implements CodesService {

	@Autowired
	private PositionRepository positionRepository;

	@Override
	public List<Position> getAllPositions() {
		return StreamSupport.stream(positionRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	@Override
	public List<Position> getAllPositionOrderByTitleDesc() {
		List<Position> positions = new ArrayList<>();
		positionRepository.findAllPositionByOrderByTitleAsc().forEach(positions::add);		
		return positions;
	}

	@Override
	public Optional<Position> getPosition(Long id) {
		return positionRepository.findById(id);
	}

	@Override
	@Transactional
	public Position savePosition(Position role) {
		return positionRepository.save(role);
	}

	@Override
	@Transactional
	public void deletePosition(Long id) {	
		positionRepository.deleteById(id);
	}

}
