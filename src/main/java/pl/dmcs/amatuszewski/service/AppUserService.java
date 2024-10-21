package pl.dmcs.amatuszewski.service;

import org.springframework.security.access.annotation.Secured;
import pl.dmcs.amatuszewski.domain.AppUser;

import java.util.List;

public interface AppUserService {
    void addAppUser(AppUser user);
    List<AppUser> listAppUser();
    AppUser getAppUser(long id);
    AppUser findByLogin(String login);
    AppUser findByEmail(String email);
    AppUser findByPesel(String pesel);
    void updateAppUser(AppUser user);
    void saveUserRole(AppUser user);
    List<AppUser> listAppUserByRole(String role);
    boolean userExists(String login);

    @Secured("ROLE_ADMIN")
    void removeAppUser(long id);
}
