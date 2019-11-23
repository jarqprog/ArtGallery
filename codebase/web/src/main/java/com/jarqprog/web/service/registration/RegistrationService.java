package com.jarqprog.web.service.registration;

import com.jarqprog.domainperson.model.user.User;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationService {

    User registerUser(RegistrationFormDTO registrationFormDTO);

}
