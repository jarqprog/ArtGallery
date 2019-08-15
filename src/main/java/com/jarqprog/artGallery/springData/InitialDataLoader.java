package com.jarqprog.artGallery.springData;

import com.jarqprog.artGallery.domain.entity.*;
import com.jarqprog.artGallery.domain.exception.ResourceNotFoundException;
import com.jarqprog.artGallery.springData.repository.*;
import com.jarqprog.artGallery.springSecurity.userDetails.SimpleUserDetailsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


//only use in development context
@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = Logger.getLogger(InitialDataLoader.class);

    boolean alreadySetup = false;

    @Autowired private UserRepository userRepository;

    @Autowired private RoleRepository roleRepository;

    @Autowired private ContactRepository contactRepository;

    @Autowired private PictureRepository pictureRepository;

    @Autowired private CommentaryRepository commentaryRepository;

    @Autowired private AuthorRepository authorRepository;

    @Autowired private SimpleUserDetailsService simpleUserDetailsService;

    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup) {
            return;
        }

        logger.info("#####Developer mode: Initialing basic data...");

//        initRoles();
//        initSuperAdmin();
//        initAdmin();
//        initJelena();
//        initSomeContacts();

        logger.info("#####Developer mode: Basic data initialized.");

        alreadySetup = true;
    }


    /**
     * Jelena is my wife and artist. I'm developing this app for her :)
     */
    @Transactional
    private void initJelena() {
        Contact jelena = new Contact("Jelena", "Kucharczyk");
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
        contacts.add(new Contact("Mark", "Smith"));
        contacts.add(new Contact("John", "Legend"));
        contacts.add(new Contact("Peter", "Miller"));
        contacts.add(new Contact("Ann", "Bigot"));
        contacts.add(new Contact("Mary", "Levis"));
        contactRepository.saveAll(contacts);
    }

    private void initRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(Roles.SUPER_ADMIN));
        roles.add(new Role(Roles.ADMIN));
        roles.add(new Role(Roles.USER));
        roleRepository.saveAll(roles);
    }

    @Transactional
    private void initSuperAdmin() {
        Contact superAdminContact = new Contact("super admin");
        contactRepository.save(superAdminContact);
        createUser(superAdminContact, Roles.SUPER_ADMIN, "super_admin", "super_admin");
    }

    @Transactional
    private void initAdmin() {
        Contact adminContact = new Contact("admin");
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
