package com.jarqprog.web.service.registration;

import com.jarqprog.domainperson.model.contact.Contact;
import com.jarqprog.domainperson.model.contact.DomainContact;
import com.jarqprog.domainperson.model.user.DomainUser;
import com.jarqprog.domainperson.model.user.User;
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
                .login(registrationFormDTO.getLogin())
                .password(registrationFormDTO.getPassword())
                .contact(contact4User)
                .build();

//        userService.addUser(user);
        return user;
    }
}
