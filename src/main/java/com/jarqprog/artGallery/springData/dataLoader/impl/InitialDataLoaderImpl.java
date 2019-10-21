package com.jarqprog.artGallery.springData.dataLoader.impl;


import com.jarqprog.artGallery.domain.entity.*;
import com.jarqprog.artGallery.springData.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.springData.dataLoader.InitialDataLoader;
import com.jarqprog.artGallery.springData.repository.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(InitialDataLoaderImpl.class);

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
        initContactUserPicturesCommentaries();
        initSomeContacts();
        logger.info("populating DB - DONE!");
    }

    @Transactional
    private void initContactUserPicturesCommentaries() {
        Contact contact = new Contact("Betty", "Sue", "bettys@gmail.com");
        contact.setNickname("betty80");
        contactRepository.save(contact);

        User user = createUser(contact, Roles.USER, "betty80", "betty80");

        Author author = new Author(contact);
        author.setArtisticNickname("betty-artist");
        authorRepository.save(author);

        Picture spring = new Picture();
        spring.setTitle("Spring");
        spring.setAuthor(author);
        pictureRepository.save(spring);

        Picture summer = new Picture();
        summer.setTitle("Summer");
        summer.setAuthor(author);
        pictureRepository.save(summer);

        Commentary firstCommentary = new Commentary("This is my first painting");
        firstCommentary.setUser(user);
        firstCommentary.setPicture(spring);

        Commentary secondCommentary = new Commentary("Do you like it?");
        secondCommentary.setUser(user);
        secondCommentary.setPicture(spring);

        Commentary thirdCommentary = new Commentary("Enjoy!");
        thirdCommentary.setUser(user);
        thirdCommentary.setPicture(spring);

        Commentary commentary4Summer = new Commentary("I love summer!");
        commentary4Summer.setUser(user);
        commentary4Summer.setPicture(summer);

        commentaryRepository.saveAll(List.of(firstCommentary, secondCommentary, thirdCommentary, commentary4Summer));
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
