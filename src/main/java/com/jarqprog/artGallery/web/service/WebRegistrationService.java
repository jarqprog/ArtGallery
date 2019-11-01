package com.jarqprog.artGallery.web.service;

import com.jarqprog.artGallery.api.domains.useCase.registration.ContactRegistration;
import com.jarqprog.artGallery.domain.personal.Contact;
import com.jarqprog.artGallery.domain.personal.User;
import com.jarqprog.artGallery.domain.personal.UserData;
import com.jarqprog.artGallery.api.domains.useCase.registration.RegistrationFormDTO;
import com.jarqprog.artGallery.api.domains.useCase.registration.UserRegistration;
import com.jarqprog.artGallery.api.domains.useCase.registration.RegistrationService;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
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
