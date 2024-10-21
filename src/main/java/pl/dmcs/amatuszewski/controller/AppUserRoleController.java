package pl.dmcs.amatuszewski.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.dmcs.amatuszewski.domain.AppUser;
import pl.dmcs.amatuszewski.domain.AppUserRole;
import pl.dmcs.amatuszewski.service.AppUserRoleService;
import pl.dmcs.amatuszewski.service.AppUserService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.*;

@Controller
public class AppUserRoleController {

    private static final Logger logger = LoggerFactory.getLogger(AppUserRoleController.class);

    private final AppUserRoleService appUserRoleService;
    private final AppUserService appUserService;

    @Autowired
    public AppUserRoleController(AppUserRoleService appUserRoleService, AppUserService appUserService) {
        this.appUserRoleService = appUserRoleService;
        this.appUserService = appUserService;
    }

    @RequestMapping(value = "/appUserRole")
    public String showUserRole(Model model) {
        model.addAttribute("appUserRole", new AppUserRole());
        model.addAttribute("roles", appUserRoleService.listAppUserRole());
        model.addAttribute("users", appUserService.listAppUser());

        Map<Long, String> userRoles = new HashMap<>();
        for (AppUser user : appUserService.listAppUser()) {
            StringBuilder roles = new StringBuilder();
            for (AppUserRole role : user.getAppUserRole()) {
                if (roles.length() > 0) {
                    roles.append(", ");
                }
                roles.append(role.getRole());
            }
            userRoles.put(user.getId(), roles.toString());
        }
        model.addAttribute("userRoles", userRoles);

        return "appUserRole";
    }


    @RequestMapping(value = "/addAppUserRole", method = RequestMethod.POST)
    public String addUserRole(@ModelAttribute("appUserRole") AppUserRole appUserRole) {
        appUserRoleService.addAppUserRole(appUserRole);
        return "redirect:/appUserRole";
    }

    @RequestMapping(value = "/editAppUserRole/{id}", method = RequestMethod.GET)
    public String showEditUserRolePage(@PathVariable("id") long id, Model model) {
        AppUserRole role = appUserRoleService.getAppUserRole(id);
        model.addAttribute("appUserRole", role);
        return "editUserRole";
    }

    @RequestMapping(value = "/editAppUserRole", method = RequestMethod.POST)
    public String editUserRole(@ModelAttribute("appUserRole") AppUserRole appUserRole) {
        appUserRoleService.updateAppUserRole(appUserRole);
        return "redirect:/appUserRole";
    }

    @RequestMapping(value = "/deleteAppUserRole/{id}", method = RequestMethod.GET)
    public String deleteUserRole(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes) {
        if (appUserRoleService.isRoleAssigned(id)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Cannot delete role as it is assigned to one or more users.");
            return "redirect:/appUserRole";
        }
        appUserRoleService.deleteAppUserRole(id);
        return "redirect:/appUserRole";
    }


    @RequestMapping(value = "/assignRole/{userId}", method = RequestMethod.GET)
    public String showAssignRolePage(@PathVariable("userId") long userId, Model model, HttpServletRequest request) {
        AppUser appUser = appUserService.getAppUser(userId);
        model.addAttribute("appUser", appUser);
        model.addAttribute("roles", appUserRoleService.listAppUserRole());

        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrfToken != null) {
            model.addAttribute("_csrf", csrfToken);
        }

        return "assignRole";
    }

    @RequestMapping(value = "/assignRole", method = RequestMethod.POST)
    public String assignRoleToUser(@RequestParam("id") long userId, @RequestParam("roleIds") List<Long> roleIds, HttpServletRequest request) {
        AppUser appUser = appUserService.getAppUser(userId);
        if (appUser != null) {
            Set<AppUserRole> newRoles = new HashSet<>();
            for (Long roleId : roleIds) {
                AppUserRole role = appUserRoleService.getAppUserRole(roleId);
                if (role != null) {
                    newRoles.add(role);
                }
            }
            appUser.setAppUserRole(newRoles);
            appUserService.saveUserRole(appUser);
        } else {
            System.out.println("AppUser is null");
        }
        return "redirect:/appUserRole";
    }



}
