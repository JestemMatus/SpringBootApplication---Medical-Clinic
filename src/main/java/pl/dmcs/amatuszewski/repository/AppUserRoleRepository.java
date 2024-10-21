package pl.dmcs.amatuszewski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.amatuszewski.domain.AppUserRole;



@Transactional
@Repository
public interface AppUserRoleRepository extends JpaRepository<AppUserRole, Long>{
    AppUserRole findByRole(String role);
}

