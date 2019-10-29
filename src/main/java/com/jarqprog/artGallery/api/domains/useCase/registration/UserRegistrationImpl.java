package com.jarqprog.artGallery.api.domains.useCase.registration;

import com.jarqprog.artGallery.domain.personal.Contact;
import com.jarqprog.artGallery.domain.personal.User;
import com.jarqprog.artGallery.api.domains.personal.user.dto.UserThin;
import com.jarqprog.artGallery.api.domains.personal.roleUser.RoleUserService;
import com.jarqprog.artGallery.api.domains.personal.user.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationImpl implements UserRegistration {

    @NonNull private final UserService userService;
    @NonNull private final RoleUserService roleUserService;

    @Autowired
    public UserRegistrationImpl(@NonNull UserService userService,
                                @NonNull RoleUserService roleUserService) {
        this.userService = userService;
        this.roleUserService = roleUserService;
    }


    @Override
    public User createUserFromRegistration(@NonNull RegistrationForm registrationForm,
                                           @NonNull Contact contact) {
        User user = new UserThin(registrationForm.getLogin(), contact.getId());
        user.setPassword(registrationForm.getPassword());
        long id = userService.addUser(user);
        user.setId(id);
        return user;
    }
}
