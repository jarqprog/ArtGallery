package com.jarqprog.personapi.seederdb;


import com.jarqprog.domainperson.model.SystemRole;
import com.jarqprog.domainperson.model.contact.Contact;
import com.jarqprog.domainperson.model.contact.ContactData;
import com.jarqprog.domainperson.model.contact.DomainContact;
import com.jarqprog.domainperson.model.user.DomainUser;
import com.jarqprog.domainperson.model.user.User;
import com.jarqprog.domainperson.model.user.UserData;
import com.jarqprog.personapi.domains.contact.ContactService;
import com.jarqprog.personapi.domains.user.UserService;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.jarqprog.commonapi.constants.seeder.PersonDomainInitData.*;


@Component
public class SeedingPersonDomainImpl implements SeedingPersonDomain {

    private static final Logger logger = LoggerFactory.getLogger(SeedingPersonDomainImpl.class);

    @NonNull private final ContactService contactService;
    @NonNull private final UserService userService;

    @Autowired
    public SeedingPersonDomainImpl(@NonNull ContactService contactService, @NonNull UserService userService) {
        this.contactService = contactService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void populateDb() {
        logger.info("populating DB - START...");
        initSuperAdmin();
        initAdmin();
        initBetty();
        initSomeContacts();
        logger.info("populating DB - DONE!");
    }

    private void initBetty() {
        Contact contact = DomainContact
                .createWith()
                .firstName(BETTY_NAME)
                .lastName(BETTY_LAST_NAME)
                .email(BETTY_MAIL)
                .build();

        long contactID = contactService.addContact(contact);

        Contact updated = DomainContact.mergeID(contactID, contact);

        User user = DomainUser.createWith()
                .contact(updated)
                .login(BETTY_LOGIN)
                .password(BETTY_PASSWORD)
                .build();

        userService.addUser(user);
    }


    private void initSomeContacts() {
        List<ContactData> contacts = new ArrayList<>();
        contacts.add(DomainContact
                .createWith()
                .firstName("Mark")
                .lastName("Smith")
                .email("mark@gmail.com")
                .build());

        contacts.add(DomainContact
                .createWith()
                .firstName("John")
                .lastName("Legend")
                .email("john@gmail.com")
                .build());

        contacts.add(DomainContact
                .createWith()
                .firstName("Peter")
                .lastName("Miller")
                .email("peter@gmail.com")
                .build());

        contacts.forEach(contactService::addContact);
    }

    private void initSuperAdmin() {
        createUser(SystemRole.SUPER_ADMIN, "Super admin",
                "super-adminContact@mail.com", "super-admin",
                "super-admin");
    }

    private void initAdmin() {
        createUser(SystemRole.ADMIN, "Admin",
                "adminContact@mail.com", "admin",
                "admin");
    }

    private void createUser(SystemRole role, String firstName,
                            String email, String login, String password) {
        Contact contact = DomainContact.createWith()
                .firstName(firstName)
                .email(email)
                .build();

        long contactID = contactService.addContact(contact);

        Contact updated = DomainContact.mergeID(contactID, contact);
        logger.info("Contact {} saved", updated.toString());

        UserData userData = DomainUser.createWith()
                .contact(updated)
                .login(login)
                .password(password)
                .build();
        userService.createUserWithRole(userData, role);
        logger.info("User {} saved", userData.toString());
        logger.info("***************************************************");
    }

}
