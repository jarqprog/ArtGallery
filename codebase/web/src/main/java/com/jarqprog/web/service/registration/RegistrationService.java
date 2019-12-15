package com.jarqprog.web.service.registration;

import com.jarqprog.domainperson.registration.RegistrationFormDTO;
import com.jarqprog.domainperson.user.User;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationService {

    User registerUser(RegistrationFormDTO registrationFormDTO);

}
