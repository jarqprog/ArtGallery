package com.jarqprog.web.service.registration;

import com.jarqprog.domainperson.contact.Contact;
import com.jarqprog.domainperson.contact.DomainContact;
import com.jarqprog.domainperson.registration.RegistrationFormDTO;
import com.jarqprog.domainperson.user.DomainUser;
import com.jarqprog.domainperson.user.User;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationImpl implements UserRegistration {

//    @NonNull private final UserService userService;
//
//    @Autowired
//    public UserRegistrationImpl(@NonNull UserService userService) {
//        this.userService = userService;
//    }


    @Override
    public User createUserFromRegistration(@NonNull RegistrationFormDTO registrationFormDTO,
                                           @NonNull Contact contact) {

        Contact contact4User = DomainContact.fromContact(contact);

        User user = DomainUser
                .createWith()
                .login(registrationFormDTO.login())
                .password(registrationFormDTO.password())
                .contact(contact4User)
                .build();

//        userService.addUser(user);
        return user;
    }
}
