package com.jarqprog.artGallery.springWebMVC.service;

import com.jarqprog.artGallery.domain.dto.ContactDTO;
import com.jarqprog.artGallery.domain.dto.UserDTO;
import com.jarqprog.artGallery.domain.dto.useCaseDTO.RegistrationDTO;
import com.jarqprog.artGallery.springData.components.ContactCreator;
import com.jarqprog.artGallery.springData.components.UserCreator;
import com.jarqprog.artGallery.domain.useCases.RegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class WebRegistrationService implements RegistrationService {

    private final UserCreator userCreator;
    private final ContactCreator contactCreator;

    @Autowired
    public WebRegistrationService(UserCreator userCreator, ContactCreator contactCreator) {
        this.userCreator = userCreator;
        this.contactCreator = contactCreator;
    }

    @Transactional
    @Override
    public UserDTO registerUser(RegistrationDTO registrationDTO) {
        ContactDTO contactDTO = contactCreator.createContactFromRegistrationDTO(registrationDTO);
        return userCreator.createUserFromRegistrationDTO(registrationDTO, contactDTO);
    }
}
