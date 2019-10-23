package com.jarqprog.artGallery.springData.components.implementation;

import com.jarqprog.artGallery.domain.dto.heavyDto.ContactDTO;
import com.jarqprog.artGallery.domain.dto.heavyDto.RoleDTO;
import com.jarqprog.artGallery.domain.dto.heavyDto.UserDTO;
import com.jarqprog.artGallery.domain.dto.useCaseDTO.RegistrationDTO;
import com.jarqprog.artGallery.domain.entity.Roles;
import com.jarqprog.artGallery.domain.dto.DtoConverter;
import com.jarqprog.artGallery.springData.components.UserRegistrationValidator;
import com.jarqprog.artGallery.springData.components.UserCreator;
import com.jarqprog.artGallery.domain.useCases.RoleService;
import com.jarqprog.artGallery.domain.useCases.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCreatorImpl implements UserCreator {

    private final DtoConverter dtoConverter;//todo to check
    private final UserRegistrationValidator userRegistrationValidator;
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserCreatorImpl(DtoConverter dtoConverter,
                           UserRegistrationValidator userRegistrationValidator,
                           UserService userService,
                           RoleService roleService) {
        assert dtoConverter != null;
        assert userRegistrationValidator != null;
        assert userService != null;
        assert roleService != null;
        this.dtoConverter = dtoConverter;
        this.userRegistrationValidator = userRegistrationValidator;
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public UserDTO createUserFromRegistrationDTO(RegistrationDTO registrationDTO, ContactDTO contactDTO) {
        userRegistrationValidator.validateUserData(registrationDTO);
        RoleDTO userRoleDTO = roleService.findByRole(Roles.USER);
        UserDTO userDTO = new UserDTO(userRoleDTO);
        userDTO.setContact(contactDTO);
        userDTO.setLogin(registrationDTO.getLogin());
        userDTO.setPassword(registrationDTO.getPassword());
        return userService.addUser(userDTO);
    }
}
