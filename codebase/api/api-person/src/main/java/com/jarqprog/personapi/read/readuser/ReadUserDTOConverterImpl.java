package com.jarqprog.personapi.read.readuser;

import com.jarqprog.personapi.domains.contact.dto.ContactThin;
import com.jarqprog.personapi.domains.user.dto.UserThin;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jarqprog.domainperson.readuser.ReadUser;

@Component
public class ReadUserDTOConverterImpl implements ReadUserDTOConverter {

    @NonNull private final ModelMapper modelMapper;

    @Autowired
    public ReadUserDTOConverterImpl(@NonNull ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiReadUserDTO mapToDTO(ReadUser domainReadUser) {
        return ApiReadUserDTOImpl.createWith()
                .user(modelMapper.map(domainReadUser.user(), UserThin.class))
                .contact(domainReadUser.contact() == null ? null : modelMapper
                        .map(domainReadUser.contact(), ContactThin.class))
                .roles(domainReadUser.roles())
                .build();
    }
}
