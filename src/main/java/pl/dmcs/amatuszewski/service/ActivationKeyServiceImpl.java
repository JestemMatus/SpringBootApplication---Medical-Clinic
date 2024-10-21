package pl.dmcs.amatuszewski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.amatuszewski.domain.ActivationKey;
import pl.dmcs.amatuszewski.domain.AppUser;
import pl.dmcs.amatuszewski.repository.ActivationKeyRepository;
import pl.dmcs.amatuszewski.repository.AppUserRepository;

import java.util.UUID;

@Service
public class ActivationKeyServiceImpl implements ActivationKeyService {
    private final ActivationKeyRepository activationKeyRepository;
    private final AppUserRepository appUserRepository;

    @Autowired
    public ActivationKeyServiceImpl(ActivationKeyRepository activationKeyRepository, AppUserRepository appUserRepository) {
        this.activationKeyRepository = activationKeyRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    @Transactional
    public void createActivationKey(AppUser user) {
        ActivationKey activationKey = new ActivationKey();
        activationKey.setAppUser(user);
        activationKey.setActivationKey(UUID.randomUUID().toString());
        activationKeyRepository.save(activationKey);
        System.out.println("Activation link: http://localhost:8080/activation/activate?key=" + activationKey.getActivationKey());
    }

    @Override
    @Transactional
    public void activateUser(String activationKey) {
        ActivationKey key = activationKeyRepository.findByActivationKey(activationKey);
        if (key != null) {
            AppUser user = key.getAppUser();
            user.setIsActive(true);
            appUserRepository.save(user);
            activationKeyRepository.delete(key);
        }
    }
}
