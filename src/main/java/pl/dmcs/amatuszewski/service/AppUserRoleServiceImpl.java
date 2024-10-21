package pl.dmcs.amatuszewski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.amatuszewski.domain.AppUserRole;
import pl.dmcs.amatuszewski.repository.AppUserRoleRepository;
import pl.dmcs.amatuszewski.repository.AppUserRepository;

import java.util.List;


@Service("appUserRoleService")
public class AppUserRoleServiceImpl implements AppUserRoleService {

    private final AppUserRoleRepository appUserRoleRepository;
    private final AppUserRepository appUserRepository;


    @Autowired
    public AppUserRoleServiceImpl(AppUserRoleRepository appUserRoleRepository, AppUserRepository appUserRepository) {
        this.appUserRoleRepository = appUserRoleRepository;
        this.appUserRepository = appUserRepository;
    }

    @Transactional
    @Override
    public void addAppUserRole(AppUserRole appUserRole) {
        appUserRoleRepository.save(appUserRole);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AppUserRole> listAppUserRole() {
        return appUserRoleRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public AppUserRole getAppUserRole(long id) {
        return appUserRoleRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void deleteAppUserRole(long id) {
        appUserRoleRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateAppUserRole(AppUserRole appUserRole) {
        AppUserRole existingRole = appUserRoleRepository.findById(appUserRole.getId()).orElseThrow(() -> new IllegalArgumentException("Role not found"));
        existingRole.setRole(appUserRole.getRole());
        appUserRoleRepository.save(existingRole);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isRoleAssigned(long roleId) {
        return appUserRepository.findAll().stream()
                .anyMatch(user -> user.getAppUserRole().stream()
                        .anyMatch(role -> role.getId() == roleId));
    }

    @Transactional(readOnly = true)
    @Override
    public AppUserRole findByRole(String role) {
        return appUserRoleRepository.findByRole(role);
    }
}

