package pl.dmcs.amatuszewski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.amatuszewski.domain.ActivationKey;

@Repository
@Transactional
public interface ActivationKeyRepository extends JpaRepository<ActivationKey, Long> {
    ActivationKey findByActivationKey(String activationKey);
}
