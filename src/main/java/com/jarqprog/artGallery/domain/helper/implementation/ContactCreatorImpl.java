package com.jarqprog.artGallery.domain.helper.implementation;


import com.jarqprog.artGallery.domain.dto.ContactDTO;
import com.jarqprog.artGallery.domain.dto.useCaseDTO.RegistrationDTO;
import com.jarqprog.artGallery.domain.helper.ContactCreator;
import com.jarqprog.artGallery.domain.helper.DtoEntityConverter;
import com.jarqprog.artGallery.domain.helper.UserRegistrationValidator;
import com.jarqprog.artGallery.domain.useCases.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactCreatorImpl implements ContactCreator {

    private final DtoEntityConverter dtoEntityConverter;
    private final UserRegistrationValidator userRegistrationValidator;
    private final ContactService contactService;

    @Autowired
    public ContactCreatorImpl(DtoEntityConverter dtoEntityConverter,
                              UserRegistrationValidator userRegistrationValidator,
                              ContactService contactService) {
        assert dtoEntityConverter != null;
        assert userRegistrationValidator != null;
        assert contactService != null;
        this.dtoEntityConverter = dtoEntityConverter;
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
