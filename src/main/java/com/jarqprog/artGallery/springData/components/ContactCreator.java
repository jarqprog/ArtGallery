package com.jarqprog.artGallery.springData.components;

import com.jarqprog.artGallery.domain.dto.heavyDto.ContactDTO;
import com.jarqprog.artGallery.domain.dto.useCaseDTO.RegistrationDTO;

public interface ContactCreator {

    ContactDTO createContactFromRegistrationDTO(RegistrationDTO registrationDTO);
}
