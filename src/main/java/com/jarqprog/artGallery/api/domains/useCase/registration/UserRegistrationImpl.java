package com.jarqprog.artGallery.api.domains.useCase.registration;

import com.jarqprog.artGallery.domain.personal.*;
import com.jarqprog.artGallery.api.domains.personal.user.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationImpl implements UserRegistration {

    @NonNull private final UserService userService;

    @Autowired
    public UserRegistrationImpl(@NonNull UserService userService) {
        this.userService = userService;
    }


    @Override
    public User createUserFromRegistration(@NonNull RegistrationFormDTO registrationFormDTO,
                                               @NonNull Contact contact) {

        Contact contact4User = DomainContact.fromContact(contact);

        User user = DomainUser
                .createWith()
                .login(registrationFormDTO.getLogin())
                .password(registrationFormDTO.getPassword())
                .contact(contact4User)
                .build();

        userService.addUser(user);
        return user;
    }
}
