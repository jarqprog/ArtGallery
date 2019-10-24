package com.jarqprog.artGallery.springWebMVC.service;

import com.jarqprog.artGallery.domain.dto.heavyDto.ContactDTO;
import com.jarqprog.artGallery.domain.dto.heavyDto.UserDTO;
import com.jarqprog.artGallery.domain.dto.useCaseDTO.RegistrationDTO;
import com.jarqprog.artGallery.springData.useCases.specials.ContactCreator;
import com.jarqprog.artGallery.springData.useCases.specials.UserCreator;
import com.jarqprog.artGallery.springData.useCases.RegistrationService;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class WebRegistrationService implements RegistrationService {

    @NonNull private final UserCreator userCreator;
    @NonNull private final ContactCreator contactCreator;

    @Autowired
    public WebRegistrationService(@NonNull UserCreator userCreator,
                                  @NonNull ContactCreator contactCreator) {
        this.userCreator = userCreator;
        this.contactCreator = contactCreator;
    }

    @Override
    public UserDTO registerUser(RegistrationDTO registrationDTO) {
        ContactDTO contactDTO = contactCreator.createContactFromRegistrationDTO(registrationDTO);
        return userCreator.createUserFromRegistrationDTO(registrationDTO, contactDTO);
    }
}
