package com.jarqprog.artGallery.springData.components.implementation;


import com.jarqprog.artGallery.domain.dto.heavyDto.ContactDTO;
import com.jarqprog.artGallery.domain.dto.useCaseDTO.RegistrationDTO;
import com.jarqprog.artGallery.springData.components.ContactCreator;
import com.jarqprog.artGallery.domain.dto.DtoConverter;
import com.jarqprog.artGallery.springData.components.UserRegistrationValidator;
import com.jarqprog.artGallery.domain.useCases.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactCreatorImpl implements ContactCreator {

    private final DtoConverter dtoConverter;
    private final UserRegistrationValidator userRegistrationValidator;
    private final ContactService contactService;

    @Autowired
    public ContactCreatorImpl(DtoConverter dtoConverter,
                              UserRegistrationValidator userRegistrationValidator,
                              ContactService contactService) {
        assert dtoConverter != null;
        assert userRegistrationValidator != null;
        assert contactService != null;
        this.dtoConverter = dtoConverter;
        this.userRegistrationValidator = userRegistrationValidator;
        this.contactService = contactService;
    }

    @Override
    public ContactDTO createContactFromRegistrationDTO(RegistrationDTO registrationDTO) {
        userRegistrationValidator.validateContactData(registrationDTO);
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setFirstName(registrationDTO.getFirstName());
        contactDTO.setLastName(registrationDTO.getLastName());
        contactDTO.setEmail(registrationDTO.getEmail());
        contactDTO.setNickname(registrationDTO.getNickname());
        return contactService.addContact(contactDTO);
    }
}
