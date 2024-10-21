package pl.dmcs.amatuszewski.service;

import pl.dmcs.amatuszewski.domain.AppUser;

public interface ActivationKeyService {
    void createActivationKey(AppUser user);
    void activateUser(String activationKey);
}
