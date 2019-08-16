package com.jarqprog.artGallery.domain.helper;

import com.jarqprog.artGallery.domain.dto.useCaseDTO.RegistrationDTO;
import com.jarqprog.artGallery.domain.exception.CreationException;

public interface RegistrationValidator {

    void validateContactData(RegistrationDTO registrationDTO) throws CreationException;
    void validateUserData(RegistrationDTO registrationDTO) throws CreationException;
}
