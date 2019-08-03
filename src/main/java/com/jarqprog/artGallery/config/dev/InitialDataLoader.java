package com.jarqprog.artGallery.config.dev;

import com.jarqprog.artGallery.domain.Contact;
import com.jarqprog.artGallery.domain.User;
import com.jarqprog.artGallery.repository.ContactRepository;
import com.jarqprog.artGallery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {


    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup) {
            return;
        }

        Contact adminContact = new Contact("admin");
        contactRepository.save(adminContact);

        userRepository.save(new User(adminContact, "admin", "admin"));
        alreadySetup = true;
    }
}
