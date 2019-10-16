package com.jarqprog.artGallery.springData.components;

import com.jarqprog.artGallery.domain.dto.useCaseDTO.RegistrationDTO;
import com.jarqprog.artGallery.springData.exceptions.CreationException;

public interface UserRegistrationValidator {

    void validateContactData(RegistrationDTO registrationDTO) throws CreationException;
    void validateUserData(RegistrationDTO registrationDTO) throws CreationException;
}
