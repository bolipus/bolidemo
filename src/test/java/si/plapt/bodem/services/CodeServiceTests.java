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

import si.plapt.bodem.entities.Position;
import si.plapt.bodem.repositories.PositionRepository;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CodeServiceTests {
	
	@MockBean
	private PositionRepository positionRepository;
	
	@Autowired
	private CodesService codeService;
	
	
	@Test
	public void getAllPositionsTest() {
		Position position = new Position(1l,"Test","Test");
		Position position2 = new Position(2l,"Test2","Test2");
		
		List<Position> positions = new ArrayList<>();
		positions.add(position);
		positions.add(position2);
		
		when(positionRepository.findAll()).thenReturn(positions);
		
		List<Position> actualPositions = codeService.getAllPositions();	
		
		assertTrue(actualPositions.size() ==  positions.size());
		assertTrue(actualPositions.get(0).getId() ==  positions.get(0).getId());
	}
	
	@Test
	public void fetchPositionTest() {
		Position position = new Position(1l,"Test","Test");
		
		when(positionRepository.findById(1L)).thenReturn(Optional.of(position));
		
		Optional<Position> actualPosition = codeService.getPosition(1l);
		
		assertTrue(actualPosition.isPresent());
		
		assertTrue(position.getId() == actualPosition.get().getId());
		
		assertEquals(position.getDescription(), actualPosition.get().getDescription());
	}

	
	
	@Test
	public void addAndUpdatePositionTest() {
		Position position = new Position(1l,"Test","Test");
		
		Position savedPosition = new Position(1l,"Test","Test");
		
		Position updatedPosition = new Position(1l,"Updated","Test");
		
		when(positionRepository.save(position))
			.thenReturn(savedPosition)
			.thenReturn(updatedPosition);
		
		
		savedPosition = codeService.savePosition(position);
		
		assertEquals(position.getName(), savedPosition.getName());
		assertEquals(position.getDescription(), savedPosition.getDescription());
		
		savedPosition.setName("Updated");
		
		updatedPosition = codeService.savePosition(position);
		
		assertEquals(savedPosition.getId(), updatedPosition.getId());
		assertEquals(savedPosition.getName(), updatedPosition.getName());
		assertNotEquals(position.getName(), updatedPosition.getName());
	}
	
	@Test
	public void deletePositionTest() {
		
		Position position = new Position(1l,"Test","Test");
		
		when(positionRepository.findById(1L)).thenReturn(Optional.of(position)).thenReturn(Optional.empty());
		
		Optional<Position> actualPosition = codeService.getPosition(1l);
		
		assertTrue(actualPosition.isPresent());
	
		codeService.deletePosition(position.getId());
		
		Optional<Position> deleted = codeService.getPosition(position.getId());
		
		assertTrue(!deleted.isPresent());
		
	}
	
	
}
