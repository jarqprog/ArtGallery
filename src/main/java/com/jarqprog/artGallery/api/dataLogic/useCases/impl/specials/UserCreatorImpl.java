package com.jarqprog.artGallery.api.dataLogic.useCases.impl.specials;

import com.jarqprog.artGallery.domain.dto.heavyDto.ContactDTO;
import com.jarqprog.artGallery.domain.dto.heavyDto.RoleDTO;
import com.jarqprog.artGallery.domain.dto.heavyDto.UserDTO;
import com.jarqprog.artGallery.domain.dto.lightDto.UserDTOLight;
import com.jarqprog.artGallery.domain.dto.useCaseDTO.RegistrationDTO;
import com.jarqprog.artGallery.domain.entity.Roles;
import com.jarqprog.artGallery.domain.dto.DtoConverter;
import com.jarqprog.artGallery.api.dataLogic.useCases.specials.UserCreator;
import com.jarqprog.artGallery.api.dataLogic.useCases.RoleService;
import com.jarqprog.artGallery.api.dataLogic.useCases.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCreatorImpl implements UserCreator {

    @NonNull private final DtoConverter dtoConverter;//todo to check
    @NonNull private final UserService userService;
    @NonNull private final RoleService roleService;

    @Autowired
    public UserCreatorImpl(@NonNull DtoConverter dtoConverter,
                           @NonNull UserService userService,
                           @NonNull RoleService roleService) {
        this.dtoConverter = dtoConverter;
        this.userService = userService;
        this.roleService = roleService;
    }


    @Override
    public UserDTO createUserFromRegistrationDTO(@NonNull RegistrationDTO registrationDTO,
                                                 @NonNull ContactDTO contactDTO) {
        RoleDTO userRoleDTO = roleService.findByRole(Roles.USER);
        UserDTOLight userDTO = new UserDTOLight(userRoleDTO.getId());
        userDTO.setContactId(contactDTO.getId());
        userDTO.setLogin(registrationDTO.getLogin());
        userDTO.setPassword(registrationDTO.getPassword());
        return userService.addUser(userDTO);
    }
}
