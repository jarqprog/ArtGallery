package com.jarqprog.artGallery.springWebMVC.service;

import com.jarqprog.artGallery.domain.dto.ContactDTO;
import com.jarqprog.artGallery.domain.dto.UserDTO;
import com.jarqprog.artGallery.domain.dto.useCaseDTO.RegistrationDTO;
import com.jarqprog.artGallery.domain.helper.ContactCreator;
import com.jarqprog.artGallery.domain.helper.UserCreator;
import com.jarqprog.artGallery.domain.useCases.RegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class WebRegistrationService implements RegistrationService {

    @Autowired private UserCreator userCreator;
    @Autowired private ContactCreator contactCreator;

    @Transactional
    @Override
    public UserDTO registerUser(RegistrationDTO registrationDTO) {
        ContactDTO contactDTO = contactCreator.createContactFromRegistrationDTO(registrationDTO);
        return userCreator.createUserFromRegistrationDTO(registrationDTO, contactDTO);
    }
}
