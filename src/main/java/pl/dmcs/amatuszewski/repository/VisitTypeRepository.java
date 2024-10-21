package pl.dmcs.amatuszewski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.amatuszewski.domain.VisitType;

@Repository
public interface VisitTypeRepository extends JpaRepository<VisitType, Long> {
}
