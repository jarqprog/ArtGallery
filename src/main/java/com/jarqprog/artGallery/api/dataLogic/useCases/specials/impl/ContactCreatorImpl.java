package com.jarqprog.artGallery.api.dataLogic.useCases.specials.impl;


import com.jarqprog.artGallery.domain.dto.ContactDTO;
import com.jarqprog.artGallery.domain.dto.fatDTO.ContactFat;
import com.jarqprog.artGallery.domain.dto.thinDTO.ContactThin;
import com.jarqprog.artGallery.domain.dto.useCaseDTO.RegistrationDTO;
import com.jarqprog.artGallery.api.dataLogic.useCases.specials.ContactCreator;
import com.jarqprog.artGallery.domain.components.DtoConverter;
import com.jarqprog.artGallery.api.dataLogic.useCases.ContactService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactCreatorImpl implements ContactCreator {

    @NonNull private final DtoConverter dtoConverter;
    @NonNull private final ContactService contactService;

    @Autowired
    public ContactCreatorImpl(@NonNull DtoConverter dtoConverter,
                              @NonNull ContactService contactService) {
        this.dtoConverter = dtoConverter;
        this.contactService = contactService;
    }


    @Override
    public ContactDTO createContactFromRegistrationDTO(RegistrationDTO registrationDTO) {
        ContactDTO contactDTO = new ContactThin();
        contactDTO.setFirstName(registrationDTO.getFirstName());
        contactDTO.setLastName(registrationDTO.getLastName());
        contactDTO.setEmail(registrationDTO.getEmail());
        contactDTO.setNickname(registrationDTO.getNickname());
        return contactService.addContact(contactDTO);
    }
}
