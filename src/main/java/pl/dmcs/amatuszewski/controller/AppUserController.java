package pl.dmcs.amatuszewski.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.dmcs.amatuszewski.domain.AppUser;
import pl.dmcs.amatuszewski.service.ActivationKeyService;
import pl.dmcs.amatuszewski.service.AppUserService;
import pl.dmcs.amatuszewski.service.ReCaptchaService;
import pl.dmcs.amatuszewski.validator.AppUserValidator;

import java.util.Locale;

@Controller
public class AppUserController {

    private final AppUserValidator appUserValidator;
    private final AppUserService appUserService;
    private final MessageSource messageSource;
    private final ActivationKeyService activationKeyService;

    private final ReCaptchaService reCaptchaService;

    @Autowired
    public AppUserController(AppUserService appUserService, MessageSource messageSource, ActivationKeyService activationKeyService, ReCaptchaService reCaptchaService) {
        this.appUserService = appUserService;
        this.messageSource = messageSource;
        this.activationKeyService = activationKeyService;
        this.appUserValidator = new AppUserValidator(appUserService);
        this.reCaptchaService = reCaptchaService;
    }

    @RequestMapping(value = "/register")
    public ModelAndView showAppUsers() {
        return new ModelAndView("register", "appUser", new AppUser());
    }

    @RequestMapping(value = "/addAppUser", method = RequestMethod.POST)
    public String addAppUser(@Valid @ModelAttribute("appUser") AppUser appUser, BindingResult result, Model model, RedirectAttributes redirectAttributes, Locale locale, HttpServletRequest request) {
        appUserValidator.validate(appUser, result);

        if (!reCaptchaService.verify(request.getParameter("g-recaptcha-response"))) {
            result.reject("recaptchaError", messageSource.getMessage("error.recaptcha.invalid", null, locale));
        }
        appUser.setIsActive(false);

        if (result.hasErrors()) {
            model.addAttribute("recaptchaError", messageSource.getMessage("error.recaptcha.invalid", null, locale));
            return "register";
        }

        if (appUser.getId() == 0) {
            appUserService.addAppUser(appUser);
            activationKeyService.createActivationKey(appUser);
            String successMessage = messageSource.getMessage("success.user.created", null, locale);
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
        }

        return "redirect:/register";
    }

    @RequestMapping(value = "/userDetails/{id}", method = RequestMethod.GET)
    public String userDetails(@PathVariable("id") long id, Model model) {
        AppUser appUser = appUserService.getAppUser(id);
        model.addAttribute("appUser", appUser);
        return "userDetails";
    }

    @RequestMapping(value = "/updateAppUser", method = RequestMethod.POST)
    public String updateAppUser(@Valid @ModelAttribute("appUser") AppUser appUser, BindingResult result, Model model) {
        appUserValidator.validateForUpdate(appUser, result, appUser.getId());

        if (result.hasErrors()) {
            System.out.println("There are errors");
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
            }
            model.addAttribute("appUser", appUser);
            return "userDetails";
        }

        AppUser existingUser = appUserService.getAppUser(appUser.getId());
        if (existingUser != null) {
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


            appUserService.updateAppUser(existingUser);
        } else {
            System.out.println("User not found with ID: " + appUser.getId());
        }

        return "redirect:/appUserRole";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, Locale locale) {
        try {
            appUserService.removeAppUser(id);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = messageSource.getMessage("error.user.cannotDeleteDueToDependencies", null, locale);
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/appUserRole";
        }
        return "redirect:/appUserRole";
    }
}
