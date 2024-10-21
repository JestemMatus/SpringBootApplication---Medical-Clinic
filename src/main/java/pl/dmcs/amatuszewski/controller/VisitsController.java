package pl.dmcs.amatuszewski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import pl.dmcs.amatuszewski.domain.AppUser;
import pl.dmcs.amatuszewski.domain.Visits;
import pl.dmcs.amatuszewski.service.VisitsService;
import pl.dmcs.amatuszewski.service.AppUserService;
import pl.dmcs.amatuszewski.service.VisitTypeService;
import pl.dmcs.amatuszewski.editor.CustomTimeEditor;
import pl.dmcs.amatuszewski.editor.CustomUtilDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class VisitsController {

    private final VisitsService visitsService;
    private final AppUserService appUserService;
    private final VisitTypeService visitTypeService;

    @Autowired
    public VisitsController(VisitsService visitsService, AppUserService appUserService, VisitTypeService visitTypeService) {
        this.visitsService = visitsService;
        this.appUserService = appUserService;
        this.visitTypeService = visitTypeService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomUtilDateEditor(dateFormat, false));

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        timeFormat.setLenient(false);
        binder.registerCustomEditor(Time.class, new CustomTimeEditor(timeFormat, false));
    }

    @RequestMapping(value = "/visits", method = RequestMethod.GET)
    public String listVisits(Model model) {
        List<Visits> visits;
        AppUser currentUser = getCurrentUser();

        if (isAdmin()) {
            visits = visitsService.listVisits();
        } else if (hasRole("ROLE_DOCTOR")) {
            visits = visitsService.listVisits().stream()
                    .filter(visit -> visit.getDoctor().getId() == currentUser.getId() && visit.getIsPayed())
                    .collect(Collectors.toList());
        } else {
            visits = visitsService.listVisitsByPatient(currentUser.getId());
        }

        model.addAttribute("visits", visits);
        model.addAttribute("currentUser", currentUser);
        return "visits";
    }

    @RequestMapping(value = "/visits/add", method = RequestMethod.GET)
    public String showAddVisitForm(Model model) {
        AppUser currentUser = getCurrentUser();
        model.addAttribute("visit", new Visits());
        model.addAttribute("doctors", appUserService.listAppUserByRole("ROLE_DOCTOR"));
        model.addAttribute("visitTypes", visitTypeService.listVisitTypes());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("minuteIntervals", List.of("00", "15", "30", "45"));
        return "addVisit";
    }

    @RequestMapping(value = "/visits/add", method = RequestMethod.POST)
    public String addVisit(@ModelAttribute("visit") Visits visit, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Validation errors occurred.");
            model.addAttribute("doctors", appUserService.listAppUserByRole("ROLE_DOCTOR"));
            model.addAttribute("visitTypes", visitTypeService.listVisitTypes());
            if (isAdmin()) {
                model.addAttribute("patients", appUserService.listAppUserByRole("ROLE_USER"));
            } else {
                AppUser currentUser = getCurrentUser();
                model.addAttribute("currentUser", currentUser);
            }
            return "addVisit";
        }

        if (!isAdmin()) {
            AppUser currentUser = getCurrentUser();
            visit.setPatient(currentUser);
        }

        visitsService.addVisit(visit);
        return "redirect:/visits";
    }

    @RequestMapping(value = "/visits/edit/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public String showEditVisitForm(@PathVariable("id") long id, Model model) {
        Visits visit = visitsService.getVisit(id);
        model.addAttribute("visit", visit);
        model.addAttribute("doctors", appUserService.listAppUserByRole("ROLE_DOCTOR"));
        model.addAttribute("visitTypes", visitTypeService.listVisitTypes());
        model.addAttribute("patients", appUserService.listAppUserByRole("ROLE_USER"));
        return "editVisit";
    }

    @RequestMapping(value = "/visits/edit", method = RequestMethod.POST)
    public String editVisit(@ModelAttribute("visit") Visits visit, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Validation errors occurred.");
            model.addAttribute("doctors", appUserService.listAppUserByRole("ROLE_DOCTOR"));
            model.addAttribute("visitTypes", visitTypeService.listVisitTypes());
            model.addAttribute("patients", appUserService.listAppUserByRole("ROLE_USER"));
            return "editVisit";
        }
        visitsService.updateVisit(visit);
        return "redirect:/visits";
    }

    @RequestMapping(value = "/visits/delete/{id}", method = RequestMethod.GET)
    public String deleteVisit(@PathVariable("id") long id, Model model) {
        visitsService.deleteVisit(id);
        return "redirect:/visits";
    }

    private boolean isAdmin() {
        return hasRole("ROLE_ADMIN");
    }

    private boolean hasRole(String role) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals(role));
    }

    private AppUser getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String login = ((UserDetails) principal).getUsername();
            return appUserService.findByLogin(login);
        }
        return null;
    }

    @RequestMapping(value = "/visits/pay/{id}", method = RequestMethod.GET)
    public String payVisit(@PathVariable("id") long id, Model model) {
        try {
            Visits visit = visitsService.getVisit(id);
            if (visit != null) {
                visit.setIsPayed(true);
                visitsService.updateVisit(visit);
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error occurred while paying for the visit.");
            return "errorPage";
        }
        return "redirect:/visits";
    }
}
