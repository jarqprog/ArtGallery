package com.jarqprog.artGallery.domain.helper.implementation;

import com.jarqprog.artGallery.domain.dto.ContactDTO;
import com.jarqprog.artGallery.domain.dto.RoleDTO;
import com.jarqprog.artGallery.domain.dto.UserDTO;
import com.jarqprog.artGallery.domain.dto.useCaseDTO.RegistrationDTO;
import com.jarqprog.artGallery.domain.entity.Roles;
import com.jarqprog.artGallery.domain.helper.DtoEntityConverter;
import com.jarqprog.artGallery.domain.helper.RegistrationValidator;
import com.jarqprog.artGallery.domain.helper.UserCreator;
import com.jarqprog.artGallery.domain.useCases.RoleService;
import com.jarqprog.artGallery.domain.useCases.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserCreatorImpl implements UserCreator {

    @Autowired private DtoEntityConverter dtoEntityConverter;
    @Autowired private RegistrationValidator registrationValidator;
    @Autowired private UserService userService;
    @Autowired private RoleService roleService;

    @Override
    public UserDTO createUserFromRegistrationDTO(RegistrationDTO registrationDTO, ContactDTO contactDTO) {
        registrationValidator.validateUserData(registrationDTO);
        RoleDTO userRoleDTO = roleService.findByRole(Roles.USER);
        UserDTO userDTO = new UserDTO(userRoleDTO);
        userDTO.setContact(contactDTO);
        userDTO.setLogin(registrationDTO.getLogin());
        userDTO.setPassword(registrationDTO.getPassword());
        return userService.addUser(userDTO);
    }
}
