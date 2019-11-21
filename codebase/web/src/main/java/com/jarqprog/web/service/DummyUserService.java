package com.jarqprog.web.service;

import com.jarqprog.domainperson.model.contact.DomainContact;
import com.jarqprog.domainperson.model.user.DomainUser;
import com.jarqprog.personapi.domains.contact.ContactEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DummyUserService {

    private static final Logger logger = LoggerFactory.getLogger(DummyUserService.class);

    public DomainUser getDummyUser() {
        final var mock = "mock";
        final var contact = ContactEntity.fromContact(DomainContact.createWith()
                .email("username@gmail.com")
                .firstName(mock)
                .build());
        final var user = DomainUser.createWith().login(mock).password(mock).contact(contact).build();
        logger.info("Dummy user created: {}", user);
        return user;
    }

}
