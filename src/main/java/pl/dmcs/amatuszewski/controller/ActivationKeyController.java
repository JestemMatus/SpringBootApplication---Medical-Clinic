package pl.dmcs.amatuszewski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.dmcs.amatuszewski.service.ActivationKeyService;

@RestController
@RequestMapping("/activation")
public class ActivationKeyController {

    private final ActivationKeyService activationKeyService;

    @Autowired
    public ActivationKeyController(ActivationKeyService activationKeyService) {
        this.activationKeyService = activationKeyService;
    }

    @GetMapping("/activate")
    public String activateUser(@RequestParam("key") String key) {
        activationKeyService.activateUser(key);
        return "User activated";
    }
}
