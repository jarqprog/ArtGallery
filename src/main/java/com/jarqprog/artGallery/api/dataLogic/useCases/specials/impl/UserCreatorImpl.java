package com.jarqprog.artGallery.api.dataLogic.useCases.specials.impl;

import com.jarqprog.artGallery.domain.dto.ContactDTO;
import com.jarqprog.artGallery.domain.dto.RoleDTO;
import com.jarqprog.artGallery.domain.dto.UserDTO;
import com.jarqprog.artGallery.domain.dto.thinDTO.UserThin;
import com.jarqprog.artGallery.domain.dto.useCaseDTO.RegistrationDTO;
import com.jarqprog.artGallery.domain.entity.AuthorizationRole;
import com.jarqprog.artGallery.api.dataLogic.useCases.specials.UserCreator;
import com.jarqprog.artGallery.api.dataLogic.useCases.RoleService;
import com.jarqprog.artGallery.api.dataLogic.useCases.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCreatorImpl implements UserCreator {

    @NonNull private final UserService userService;
    @NonNull private final RoleService roleService;

    @Autowired
    public UserCreatorImpl(@NonNull UserService userService,
                           @NonNull RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @Override
    public UserDTO createUserFromRegistrationDTO(@NonNull RegistrationDTO registrationDTO,
                                                 @NonNull ContactDTO contactDTO) {
        RoleDTO userRoleDTO = roleService.findByRole(AuthorizationRole.USER);
        UserDTO userDTO = new UserThin(userRoleDTO.getId(), contactDTO.getId());
        userDTO.setLogin(registrationDTO.getLogin());
        userDTO.setPassword(registrationDTO.getPassword());
        return userService.addUser(userDTO);
    }
}
