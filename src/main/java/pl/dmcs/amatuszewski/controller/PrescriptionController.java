package pl.dmcs.amatuszewski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.amatuszewski.domain.AppUser;
import pl.dmcs.amatuszewski.domain.Prescription;
import pl.dmcs.amatuszewski.service.AppUserService;
import pl.dmcs.amatuszewski.service.PrescriptionService;

import java.util.List;
import java.util.stream.Collectors;

import static javax.management.Query.or;

@Controller
@RequestMapping("/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    private final AppUserService appUserService;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService, AppUserService appUserService) {
        this.prescriptionService = prescriptionService;
        this.appUserService = appUserService;
    }

    @GetMapping
    public String listPrescriptions(Model model) {
        AppUser currentUser = getCurrentUser();
        List<Prescription> prescriptions;

        if (hasRole("ROLE_DOCTOR")) {
            prescriptions = prescriptionService.listPrescriptions().stream()
                    .filter(prescription -> prescription.getDoctor().getId() == currentUser.getId())
                    .collect(Collectors.toList());
        } else if (hasRole("ROLE_USER")) {
            prescriptions = prescriptionService.listPrescriptions().stream()
                    .filter(prescription -> prescription.getPatient().getId() == currentUser.getId())
                    .collect(Collectors.toList());
        } else if (hasRole("ROLE_ADMIN")){
            prescriptions = prescriptionService.listPrescriptions();

        }

        else {
            prescriptions = List.of();
        }

        model.addAttribute("prescriptions", prescriptions);
        model.addAttribute("currentUser", currentUser);
        return "listPrescriptions";
    }

    @GetMapping("/add")
    public String showAddPrescriptionForm(Model model) {
        if (!hasRole("ROLE_DOCTOR")) {
            return "redirect:/prescriptions";
        }

        model.addAttribute("prescription", new Prescription());
        model.addAttribute("doctors", List.of(getCurrentUser())); // Only the current doctor
        model.addAttribute("patients", appUserService.listAppUserByRole("ROLE_USER"));
        return "addPrescription";
    }

    @PostMapping("/add")
    public String addPrescription(@ModelAttribute("prescription") Prescription prescription) {
        if (!hasRole("ROLE_DOCTOR")) {
            return "redirect:/prescriptions";
        }

        prescription.setDoctor(getCurrentUser());
        prescriptionService.addPrescription(prescription);
        return "redirect:/prescriptions";
    }

    @GetMapping("/edit/{id}")
    public String showEditPrescriptionForm(@PathVariable("id") long id, Model model) {
        Prescription prescription = prescriptionService.getPrescription(id);
        AppUser currentUser = getCurrentUser();

        if ((hasRole("ROLE_DOCTOR") && prescription.getDoctor().getId() == currentUser.getId()) ||
                (hasRole("ROLE_USER") && prescription.getPatient().getId() == currentUser.getId())) {
            model.addAttribute("prescription", prescription);
            model.addAttribute("doctors", List.of(currentUser)); // Only the current doctor
            model.addAttribute("patients", appUserService.listAppUserByRole("ROLE_USER"));
            return "editPrescription";
        }
        return "redirect:/prescriptions";
    }

    @PostMapping("/edit")
    public String editPrescription(@ModelAttribute("prescription") Prescription prescription) {
        if (!hasRole("ROLE_DOCTOR") || prescription.getDoctor().getId() != getCurrentUser().getId()) {
            return "redirect:/prescriptions";
        }

        prescriptionService.updatePrescription(prescription);
        return "redirect:/prescriptions";
    }

    @GetMapping("/delete/{id}")
    public String deletePrescription(@PathVariable("id") long id) {
        Prescription prescription = prescriptionService.getPrescription(id);
        if (prescription.getDoctor().getId() == getCurrentUser().getId() || (hasRole("ROLE_ADMIN"))){
            prescriptionService.deletePrescription(id);
        }
        return "redirect:/prescriptions";
    }

    private AppUser getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String login = ((UserDetails) principal).getUsername();
            return appUserService.findByLogin(login);
        }
        return null;
    }

    private boolean hasRole(String role) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals(role));
    }
}
