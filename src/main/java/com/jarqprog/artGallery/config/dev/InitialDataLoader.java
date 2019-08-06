package com.jarqprog.artGallery.config.dev;

import com.jarqprog.artGallery.domain.*;
import com.jarqprog.artGallery.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {


    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private CommentaryRepository commentaryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup) {
            return;
        }

        Contact adminContact = new Contact("admin");
        contactRepository.save(adminContact);
        userRepository.save(new User(adminContact, "admin", "admin"));

        initJelena();

        alreadySetup = true;
    }


    /**
     * Jelena is my wife and artist. I'm developing this app for her :)
     */
    private void initJelena() {
        Contact jelena = new Contact("Jelena", "Kucharczyk");
        jelena.setNickname("Alenka");
        contactRepository.save(jelena);

        User userJelena = new User(jelena, "login", "pass");
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
}
