package com.jarqprog.artGallery.springData.useCases.impl.specials;


import com.jarqprog.artGallery.domain.dto.heavyDto.ContactDTO;
import com.jarqprog.artGallery.domain.dto.lightDto.ContactDTOLight;
import com.jarqprog.artGallery.domain.dto.useCaseDTO.RegistrationDTO;
import com.jarqprog.artGallery.springData.useCases.specials.ContactCreator;
import com.jarqprog.artGallery.domain.dto.DtoConverter;
import com.jarqprog.artGallery.springData.useCases.ContactService;
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
        ContactDTOLight contactDTO = new ContactDTOLight();
        contactDTO.setFirstName(registrationDTO.getFirstName());
        contactDTO.setLastName(registrationDTO.getLastName());
        contactDTO.setEmail(registrationDTO.getEmail());
        contactDTO.setNickname(registrationDTO.getNickname());
        return contactService.addContact(contactDTO);
    }
}
