package com.jarqprog.artGallery.api.dataLogic.useCases.specials;

import com.jarqprog.artGallery.domain.dto.heavyDto.ContactDTO;
import com.jarqprog.artGallery.domain.dto.useCaseDTO.RegistrationDTO;

public interface ContactCreator {

    ContactDTO createContactFromRegistrationDTO(RegistrationDTO registrationDTO);
}