package pl.dmcs.amatuszewski.controller;

import org.apache.commons.validator.routines.BigDecimalValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.dmcs.amatuszewski.domain.VisitType;
import pl.dmcs.amatuszewski.service.VisitTypeService;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

@Controller
@RequestMapping("/visit_types")
public class VisitTypeController {

    private final VisitTypeService visitTypeService;

    @Autowired
    public VisitTypeController(VisitTypeService visitTypeService) {
        this.visitTypeService = visitTypeService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        NumberFormat numberFormat = new DecimalFormat("#0.00");
        CustomNumberEditor customNumberEditor = new CustomNumberEditor(Double.class, numberFormat, true);
        binder.registerCustomEditor(Double.class, customNumberEditor);
    }

    @GetMapping
    public String listVisitTypes(Model model) {
        List<VisitType> visitTypes = visitTypeService.listVisitTypes();
        model.addAttribute("visitTypes", visitTypes);
        return "listVisitTypes";
    }

    @GetMapping("/add")
    public String showAddVisitTypeForm(Model model) {
        model.addAttribute("visitType", new VisitType());
        return "addVisitType";
    }

    @PostMapping("/add")
    public String addVisitType(@ModelAttribute("visitType") VisitType visitType, BindingResult result, Model model) {
        validateVisitType(visitType, result);
        if (result.hasErrors()) {
            return "addVisitType";
        }
        visitTypeService.addVisitType(visitType);
        System.out.println("Visit Type added: " + visitType);
        return "redirect:/visit_types";
    }

    @GetMapping("/edit/{id}")
    public String showEditVisitTypeForm(@PathVariable("id") long id, Model model) {
        VisitType visitType = visitTypeService.getVisitType(id);
        model.addAttribute("visitType", visitType);
        return "editVisitType";
    }

    @PostMapping("/edit")
    public String editVisitType(@ModelAttribute("visitType") VisitType visitType, BindingResult result, Model model) {
        validateVisitType(visitType, result);
        if (result.hasErrors()) {
            System.out.println("Validation errors occurred: " + result.getAllErrors());
            return "editVisitType";
        }
        visitTypeService.updateVisitType(visitType);
        System.out.println("Visit Type updated: " + visitType);
        return "redirect:/visit_types";
    }

    @GetMapping("/delete/{id}")
    public String deleteVisitType(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        try {
            visitTypeService.deleteVisitType(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Visit Type cannot be deleted as it is associated with other records.");
        }
        return "redirect:/visit_types";
    }

    private void validateVisitType(VisitType visitType, BindingResult result) {
        if (visitType.getServiceName() == null || visitType.getServiceName().trim().isEmpty()) {
            result.rejectValue("serviceName", "error.serviceName", "Name is required");
        }
        if (visitType.getServicePrice() == null) {
            result.rejectValue("servicePrice", "error.servicePrice", "Price must be a valid number");
        }
    }
}
