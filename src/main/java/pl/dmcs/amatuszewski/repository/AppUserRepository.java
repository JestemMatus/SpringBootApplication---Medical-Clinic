package pl.dmcs.amatuszewski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.amatuszewski.domain.AppUser;

import java.util.Optional;

@Transactional
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findById(long id);

    AppUser findByLogin(String login);

    AppUser findByEmail(String email);

    AppUser findByPesel(String pesel);

}
