package pl.dmcs.amatuszewski.service;

import pl.dmcs.amatuszewski.domain.AppUserRole;
import java.util.List;


public interface AppUserRoleService {
    void addAppUserRole(AppUserRole appUserRole);
    List<AppUserRole> listAppUserRole();
    AppUserRole getAppUserRole(long id);
    void deleteAppUserRole(long id);

    void updateAppUserRole(AppUserRole appUserRole);

    boolean isRoleAssigned(long roleId);

    AppUserRole findByRole(String role);


}

