package com.jarqprog.artGallery.api.dataLogic.components.dtoValidators;

import com.jarqprog.artGallery.domain.dto.ContactDTO;

public interface ContactValidator {

    void validateOnCreation(ContactDTO contactDTO);
    void validateOnUpdate(ContactDTO contactDTO);
    void validateEmail(final String email);
}
