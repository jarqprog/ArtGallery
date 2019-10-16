package com.jarqprog.artGallery.springData.dataLoader.impl;


import com.jarqprog.artGallery.domain.entity.*;
import com.jarqprog.artGallery.springData.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.springData.dataLoader.InitialDataLoader;
import com.jarqprog.artGallery.springData.repository.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile({"dev", "test"})
//@Profile({"dev", "test", "prod"})
public class InitialDataLoaderImpl implements InitialDataLoader {

    private static final Logger logger = Logger.getLogger(InitialDataLoaderImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ContactRepository contactRepository;
    private final PictureRepository pictureRepository;
    private final CommentaryRepository commentaryRepository;
    private final AuthorRepository authorRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitialDataLoaderImpl(UserRepository userRepository,
                                 RoleRepository roleRepository,
                                 ContactRepository contactRepository,
                                 PictureRepository pictureRepository,
                                 CommentaryRepository commentaryRepository,
                                 AuthorRepository authorRepository,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.contactRepository = contactRepository;
        this.pictureRepository = pictureRepository;
        this.commentaryRepository = commentaryRepository;
        this.authorRepository = authorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void populateDb() {

        logger.info("populating DB - START...");
        initRoles();
        initSuperAdmin();
        initAdmin();
        initJelena();
        initSomeContacts();
        logger.info("populating DB - DONE!");
    }

    /**
     * Jelena is my wife and artist. I'm developing this app for her :)
     */
    private void initJelena() {
        Contact jelena = new Contact("Jelena", "Kucharczyk", "jelenka@gmail.com");
        jelena.setNickname("Alenka");
        contactRepository.save(jelena);

        User userJelena = createUser(jelena, Roles.USER, "login", "password");

        Author authorJelena = new Author(jelena);
        authorJelena.setArtisticNickname("Alenka");
        authorRepository.save(authorJelena);

        Picture spring = new Picture();
        spring.setTitle("Wiosna");
        spring.setAuthor(authorJelena);
        pictureRepository.save(spring);

        Commentary commentary = new Commentary("Zapraszam do ogladania");
        commentary.setUser(userJelena);
        commentary.setPicture(spring);
        commentaryRepository.save(commentary);
    }

    private void initSomeContacts() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Mark", "Smith", "mark@gmail.com"));
        contacts.add(new Contact("John", "Legend", "john@gmail.com"));
        contacts.add(new Contact("Peter", "Miller", "peter@gmail.com"));
        contacts.add(new Contact("Ann", "Bigot", "ann@gmail.com"));
        contacts.add(new Contact("Mary", "Levis", "marry@gmail.com"));
        contactRepository.saveAll(contacts);
    }

    private void initRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(Roles.SUPER_ADMIN));
        roles.add(new Role(Roles.ADMIN));
        roles.add(new Role(Roles.USER));
        roleRepository.saveAll(roles);
    }

    private void initSuperAdmin() {
        Contact superAdminContact = new Contact("super admin", "superAdminContact@mail.com");
        contactRepository.save(superAdminContact);
        createUser(superAdminContact, Roles.SUPER_ADMIN, "super_admin", "super_admin");
    }

    private void initAdmin() {
        Contact adminContact = new Contact("admin", "adminContact@mail.com");
        contactRepository.save(adminContact);
        createUser(adminContact, Roles.ADMIN, "admin", "admin");
    }

    private User createUser(Contact contact, Roles role, String login, String password) {
        Role userRole = roleRepository
                .findByRole(role).orElseThrow(() -> new ResourceNotFoundException(Role.class));
        return userRepository.save(new User(contact,
                login, passwordEncoder.encode(password),
                userRole));
    }
}
