package com.jarqprog.artGallery.config.data.devConfig;

import com.jarqprog.artGallery.domain.*;
import com.jarqprog.artGallery.repository.*;
import com.jarqprog.artGallery.service.user.SimpleUserDetailsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
        logger.info("creating admin...");
        Contact adminContact = new Contact("admin");
        contactRepository.save(adminContact);

        logger.info("creating roles admin...");
        Role role = new Role(Roles.ADMIN);

        logger.info(role);

        roleRepository.save(role);
        userRepository.save(new User(adminContact, "admin", passwordEncoder.encode("admin"), role));

        initJelena();
        initSomeContacts();

        logger.info("############################## USER DETAILS: " +
                simpleUserDetailsService.loadUserByUsername("admin").toString());

        alreadySetup = true;
    }


    /**
     * Jelena is my wife and artist. I'm developing this app for her :)
     */
    private void initJelena() {
        Contact jelena = new Contact("Jelena", "Kucharczyk");
        jelena.setNickname("Alenka");
        contactRepository.save(jelena);

        Role role = new Role(Roles.USER);
        roleRepository.save(role);
        User userJelena = new User(jelena, "login", passwordEncoder.encode("pass"), role);
        userRepository.save(userJelena);

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
}
