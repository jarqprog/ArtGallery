package com.jarqprog.artGallery.api.domains.useCase.registration;

import com.jarqprog.artGallery.domain.personal.User;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationService {

    User registerUser(RegistrationForm registrationForm);

}
