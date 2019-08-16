package com.jarqprog.artGallery.domain.helper.implementation;


import com.jarqprog.artGallery.domain.dto.ContactDTO;
import com.jarqprog.artGallery.domain.dto.useCaseDTO.RegistrationDTO;
import com.jarqprog.artGallery.domain.helper.ContactCreator;
import com.jarqprog.artGallery.domain.helper.DtoEntityConverter;
import com.jarqprog.artGallery.domain.helper.RegistrationValidator;
import com.jarqprog.artGallery.domain.useCases.ContactService;
import org.springframework.beans.factory.annotation.Autowired;

public class ContactCreatorImpl implements ContactCreator {

    @Autowired private DtoEntityConverter dtoEntityConverter;
    @Autowired private RegistrationValidator registrationValidator;
    @Autowired private ContactService contactService;

    @Override
    public ContactDTO createContactFromRegistrationDTO(RegistrationDTO registrationDTO) {
        registrationValidator.validateContactData(registrationDTO);
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setFirstName(registrationDTO.getFirstName());
        contactDTO.setLastName(registrationDTO.getLastName());
        contactDTO.setEmail(registrationDTO.getEmail());
        contactDTO.setNickname(registrationDTO.getNickname());
        return contactService.addContact(contactDTO);
    }
}
