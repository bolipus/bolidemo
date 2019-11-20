package si.plapt.bodem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import si.plapt.bodem.entities.Position;

@Repository
public interface PositionRepository extends CrudRepository<Position, Long> {

	Iterable<Position> findAllPositionByOrderByNameAsc();
}
