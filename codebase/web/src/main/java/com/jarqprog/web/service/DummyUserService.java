package com.jarqprog.web.service;

import com.jarqprog.domainperson.contact.DomainContact;
import com.jarqprog.domainperson.user.DomainUser;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DummyUserService {

    private static final Logger logger = LoggerFactory.getLogger(DummyUserService.class);

    @NonNull
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DummyUserService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public DomainUser getDummyUser() {
        final var mock = "mock";
        final var contact = DomainContact.fromContact(DomainContact.createWith()
                .email("username@gmail.com")
                .firstName(mock)
                .build());
        final var user = DomainUser.createWith()
                .login(mock)
                .password(passwordEncoder.encode(mock))
                .contact(contact)
                .build();
        logger.info("Dummy user created: {}", user);
        return user;
    }
}
