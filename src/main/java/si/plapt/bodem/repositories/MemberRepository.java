package si.plapt.bodem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import si.plapt.bodem.entities.Member;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {

}
