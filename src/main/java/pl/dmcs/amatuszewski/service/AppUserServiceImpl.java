package pl.dmcs.amatuszewski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.amatuszewski.domain.AppUser;
import pl.dmcs.amatuszewski.domain.AppUserRole;
import pl.dmcs.amatuszewski.repository.AppUserRepository;
import pl.dmcs.amatuszewski.repository.AppUserRoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppUserRoleRepository appUserRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, AppUserRoleRepository appUserRoleRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appUserRoleRepository = appUserRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void addAppUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setIsActive(false);
        appUserRepository.save(appUser);

        AppUserRole userRole = appUserRoleRepository.findByRole("ROLE_USER");
        if (userRole != null && !appUser.getAppUserRole().contains(userRole)) {
            appUser.getAppUserRole().add(userRole);
            appUserRepository.save(appUser);
        }
    }

    @Transactional
    public List<AppUser> listAppUser() {
        return appUserRepository.findAll();
    }

    @Transactional
    public AppUser getAppUser(long id) {
        return appUserRepository.findById(id).orElse(null);
    }

    @Transactional
    public AppUser findByLogin(String login) {
        return appUserRepository.findByLogin(login);
    }

    @Transactional
    public AppUser findByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    @Transactional
    public AppUser findByPesel(String pesel) {
        return appUserRepository.findByPesel(pesel);
    }

    @Transactional
    public void removeAppUser(long id) {
        AppUser appUser = appUserRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        appUserRepository.delete(appUser);
    }

    @Transactional
    public void updateAppUser(AppUser appUser) {
        AppUser existingUser = appUserRepository.findById(appUser.getId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        existingUser.setLogin(appUser.getLogin());
        existingUser.setFirstName(appUser.getFirstName());
        existingUser.setLastName(appUser.getLastName());
        existingUser.setEmail(appUser.getEmail());
        existingUser.setTelephoneNumber(appUser.getTelephoneNumber());
        existingUser.setPesel(appUser.getPesel());
        existingUser.setCity(appUser.getCity());
        existingUser.setStreet(appUser.getStreet());
        existingUser.setBuildingNumber(appUser.getBuildingNumber());
        existingUser.setApartmentNumber(appUser.getApartmentNumber());
        existingUser.setDefaultClinic(appUser.getDefaultClinic());
        appUserRepository.save(existingUser);
    }

    @Transactional
    public void saveUserRole(AppUser appUser) {
        AppUser existingUser = appUserRepository.findById(appUser.getId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        existingUser.setAppUserRole(appUser.getAppUserRole());
        appUserRepository.save(existingUser);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AppUser> listAppUserByRole(String role) {
        return appUserRepository.findAll().stream()
                .filter(user -> user.getAppUserRole().stream().anyMatch(r -> r.getRole().equals(role)))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public boolean userExists(String login) {
        return appUserRepository.findByLogin(login) != null;
    }
}
