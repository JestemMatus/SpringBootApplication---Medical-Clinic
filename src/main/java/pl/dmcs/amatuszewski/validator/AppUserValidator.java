package pl.dmcs.amatuszewski.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.dmcs.amatuszewski.domain.AppUser;
import pl.dmcs.amatuszewski.service.AppUserService;

public class AppUserValidator implements Validator {

    private final EmailValidator emailValidator = EmailValidator.getInstance();
    private final AppUserService appUserService;

    public AppUserValidator(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    public String phonePattern = "\\d{3}-\\d{3}-\\d{3}";
    public String peselPattern = "\\d{11}";

    public String uppercasePattern = ".*[A-Z].*";
    public String lowercasePattern = ".*[a-z].*";
    public String specialCharacterPattern = ".*[!@#\\$%\\^&\\*].*";
    public String digitPattern = ".*\\d.*";
    public String lengthPattern = ".{8,}";

    @Override
    public boolean supports(Class<?> clazz) {
        return AppUser.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        validateAppUser(target, errors, null);
    }

    public void validateForUpdate(Object target, Errors errors, long userId) {
        validateAppUser(target, errors, userId);
    }

    private void validateAppUser(Object target, Errors errors, Long userId) {
        AppUser appUser = (AppUser) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "error.field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telephoneNumber", "error.field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pesel", "error.field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "error.field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "error.field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "buildingNumber", "error.field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "defaultClinic", "error.field.required");

        System.out.println("Validation starting...");

        if (StringUtils.hasText(appUser.getEmail()) && !emailValidator.isValid(appUser.getEmail())) {
            errors.rejectValue("email", "error.email.invalid");
        }
        if (StringUtils.hasText(appUser.getTelephoneNumber()) && !appUser.getTelephoneNumber().matches(phonePattern)) {
            errors.rejectValue("telephoneNumber", "error.telephone.invalid");
        }
        if (StringUtils.hasText(appUser.getPesel()) && !appUser.getPesel().matches(peselPattern)) {
            errors.rejectValue("pesel", "error.pesel.invalid");
        }

        if (StringUtils.hasText(appUser.getLogin())) {
            AppUser existingUser = appUserService.findByLogin(appUser.getLogin());
            if (existingUser != null && (userId == null || existingUser.getId() != userId)) {
                errors.rejectValue("login", "error.login.exists");
            }
        }

        if (StringUtils.hasText(appUser.getEmail())) {
            AppUser existingUser = appUserService.findByEmail(appUser.getEmail());
            if (existingUser != null && (userId == null || existingUser.getId() != userId)) {
                errors.rejectValue("email", "error.email.exists");
            }
        }

        if (StringUtils.hasText(appUser.getPesel())) {
            AppUser existingUser = appUserService.findByPesel(appUser.getPesel());
            if (existingUser != null && (userId == null || existingUser.getId() != userId)) {
                errors.rejectValue("pesel", "error.pesel.exists");
            }
        }

        if (StringUtils.hasText(appUser.getPassword())) {
            if (!appUser.getPassword().matches(uppercasePattern)) {
                errors.rejectValue("password", "error.password.uppercase");
            }
            if (!appUser.getPassword().matches(lowercasePattern)) {
                errors.rejectValue("password", "error.password.lowercase");
            }
            if (!appUser.getPassword().matches(specialCharacterPattern)) {
                errors.rejectValue("password", "error.password.special");
            }
            if (!appUser.getPassword().matches(digitPattern)) {
                errors.rejectValue("password", "error.password.digit");
            }
            if (!appUser.getPassword().matches(lengthPattern)) {
                errors.rejectValue("password", "error.password.length");
            }
        }


        System.out.println("Validation finished. Error count: " + errors.getErrorCount());
    }
}
