package com.jarqprog.web.service;


import com.jarqprog.web.service.registration.ContactRegistration;
import com.jarqprog.domainperson.usecase.registration.RegistrationFormDTO;
import com.jarqprog.web.service.registration.RegistrationService;
import com.jarqprog.web.service.registration.UserRegistration;
import com.jarqprog.domainperson.model.contact.Contact;
import com.jarqprog.domainperson.model.user.User;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WebRegistrationService implements RegistrationService {

    private static final Logger logger = LoggerFactory.getLogger(WebRegistrationService.class);

    @NonNull private final UserRegistration userRegistration;
    @NonNull private final ContactRegistration contactRegistration;

    @Autowired
    public WebRegistrationService(@NonNull UserRegistration userRegistration,
                                  @NonNull ContactRegistration contactRegistration) {
        this.userRegistration = userRegistration;
        this.contactRegistration = contactRegistration;
    }

    @Override
    public User registerUser(@NonNull final RegistrationFormDTO registrationFormDTO) {
        final Contact contact = contactRegistration.createContactFromRegistration(registrationFormDTO);
        return userRegistration.createUserFromRegistration(registrationFormDTO, contact);
    }
}
