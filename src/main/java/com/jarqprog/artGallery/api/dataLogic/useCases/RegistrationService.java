package com.jarqprog.artGallery.api.dataLogic.useCases;

import com.jarqprog.artGallery.domain.dto.heavyDto.UserDTO;
import com.jarqprog.artGallery.domain.dto.useCaseDTO.RegistrationDTO;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationService {

    UserDTO registerUser(RegistrationDTO registrationDTO);

}
