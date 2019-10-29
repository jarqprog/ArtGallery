package com.jarqprog.artGallery.api.infrastructure.components.dataLoader.impl;



import com.jarqprog.artGallery.api.domains.artistic.author.AuthorService;
import com.jarqprog.artGallery.api.domains.artistic.author.dto.AuthorThin;
import com.jarqprog.artGallery.api.domains.artistic.commentary.CommentaryService;
import com.jarqprog.artGallery.api.domains.artistic.commentary.dto.CommentaryThin;
import com.jarqprog.artGallery.api.domains.artistic.picture.PictureService;
import com.jarqprog.artGallery.api.domains.artistic.picture.model.PictureThin;
import com.jarqprog.artGallery.api.domains.personal.contact.ContactService;
import com.jarqprog.artGallery.api.domains.personal.contact.dto.ContactThin;
import com.jarqprog.artGallery.api.domains.personal.user.UserService;
import com.jarqprog.artGallery.api.domains.personal.user.dto.UserThin;
import com.jarqprog.artGallery.api.infrastructure.components.dataLoader.InitialDataLoader;

import com.jarqprog.artGallery.domain.personal.Contact;
import com.jarqprog.artGallery.domain.personal.SystemRole;
import com.jarqprog.artGallery.domain.personal.User;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile({"dev", "test"})
//@Profile({"dev", "test", "prod"})
public class InitialDataLoaderImpl implements InitialDataLoader {

    private static final Logger logger = LoggerFactory.getLogger(InitialDataLoaderImpl.class);

    private static final String FAKE_PICTURE_PATH = "fake/path";


    @NonNull private final AuthorService authorService;
    @NonNull private final CommentaryService commentaryService;
    @NonNull private final PictureService pictureService;

    @NonNull private final ContactService contactService;
    @NonNull private final UserService userService;

    @Autowired
    public InitialDataLoaderImpl(@NonNull AuthorService authorService,
                                 @NonNull CommentaryService commentaryService,
                                 @NonNull PictureService pictureService,
                                 @NonNull ContactService contactService,
                                 @NonNull UserService userService) {
        this.authorService = authorService;
        this.commentaryService = commentaryService;
        this.pictureService = pictureService;
        this.contactService = contactService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void populateDb() {

        logger.info("populating DB - START...");
        initSuperAdmin();
        initAdmin();
        initContactUserPicturesCommentaries();
        initSomeContacts();
        logger.info("populating DB - DONE!");
    }

    private void initContactUserPicturesCommentaries() {
        final String login = "betty80";
        Contact contact = new ContactThin("Betty", "Sue", "bettys@gmail.com");
        contact.setNickname(login);
        final long contactId = contactService.addContact(contact);

        User user = new UserThin(login, contactId);
        user.setPassword(login);
        userService.addUser(user);

        AuthorThin author = new AuthorThin("betty-artist", contactId);
        final long authorId = authorService.addAuthor(author);

        PictureThin spring = new PictureThin("Spring", login);
        spring.setAuthorId(authorId);
        spring.setPath(FAKE_PICTURE_PATH);
        final long springId = pictureService.addPicture(spring);

        PictureThin summer = new PictureThin("Summer", login);
        summer.setAuthorId(authorId);
        summer.setPath(FAKE_PICTURE_PATH);
        final long summerId = pictureService.addPicture(summer);

        CommentaryThin firstCommentary = new CommentaryThin("This is my first painting", springId);
        firstCommentary.setUserLogin(login);
        commentaryService.addCommentary(springId, firstCommentary);

        CommentaryThin secondCommentary = new CommentaryThin("Do you like it?", springId);
        secondCommentary.setUserLogin(login);
        commentaryService.addCommentary(springId, secondCommentary);

        CommentaryThin thirdCommentary = new CommentaryThin("Enjoy!", springId);
        thirdCommentary.setUserLogin(login);
        commentaryService.addCommentary(springId, thirdCommentary);

        CommentaryThin fourthCommentary = new CommentaryThin("I love summer!", summerId);
        fourthCommentary.setUserLogin(login);
        commentaryService.addCommentary(summerId, fourthCommentary);

    }

    private void initSomeContacts() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new ContactThin("Mark", "Smith", "mark@gmail.com"));
        contacts.add(new ContactThin("John", "Legend", "john@gmail.com"));
        contacts.add(new ContactThin("Peter", "Miller", "peter@gmail.com"));
        contacts.add(new ContactThin("Ann", "Bigot", "ann@gmail.com"));
        contacts.add(new ContactThin("Mary", "Levis", "marry@gmail.com"));
        contacts.forEach(contactService::addContact);
    }

    private void initSuperAdmin() {
        Contact superAdminContact = new ContactThin("super admin", "superAdminContact@mail.com");
        long contactId = contactService.addContact(superAdminContact);
        createUser(contactId, SystemRole.SUPER_ADMIN, "super_admin", "super_admin");
    }

    private void initAdmin() {
        Contact adminContact = new ContactThin("admin", "adminContact@mail.com");
        long contactId = contactService.addContact(adminContact);
        createUser(contactId, SystemRole.ADMIN, "admin", "admin");
    }

    private void createUser(long contactId, SystemRole role, String login, String password) {
        UserThin user = new UserThin(login, contactId);
        user.setPassword(password);
        userService.createUserWithRole(user, role);
    }
}
