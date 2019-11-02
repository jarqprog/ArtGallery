package com.jarqprog.artGallery.api.infrastructure.components.dataLoader.impl;



import com.jarqprog.artGallery.api.ApiConstants;
import com.jarqprog.artGallery.api.domains.artistic.author.AuthorService;
import com.jarqprog.artGallery.api.domains.artistic.commentary.CommentaryService;
import com.jarqprog.artGallery.api.domains.artistic.picture.PictureService;
import com.jarqprog.artGallery.api.domains.personal.contact.ContactService;
import com.jarqprog.artGallery.api.domains.personal.user.UserService;
import com.jarqprog.artGallery.api.infrastructure.components.dataLoader.InitialDataLoader;

import com.jarqprog.artGallery.domain.artistic.*;
import com.jarqprog.artGallery.domain.personal.*;
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
@Profile(ApiConstants.DEV_PROFILE)
public class InitialDataLoaderDev implements InitialDataLoader {

    private static final Logger logger = LoggerFactory.getLogger(InitialDataLoaderDev.class);

    @NonNull private final AuthorService authorService;
    @NonNull private final CommentaryService commentaryService;
    @NonNull private final PictureService pictureService;

    @NonNull private final ContactService contactService;
    @NonNull private final UserService userService;

    @Autowired
    public InitialDataLoaderDev(@NonNull AuthorService authorService,
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
        Contact contact = DomainContact
                .createWith()
                .firstName(BETTY_NAME)
                .lastName(BETTY_LAST_NAME)
                .nickname(BETTY_LOGIN)
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

        Author author = DomainAuthor.createWith()
                .artisticNickname(BETTY_ARTIST_NICK)
                .contactId(contactID)
                .build();

        final long authorId = authorService.addAuthor(author);

        Author updatedAuthor = DomainAuthor.mergeID(authorId, author);

        Picture spring = createPicture(updatedAuthor, user, BETTY_FIRST_PICTURE_TITLE);
        Picture summer = createPicture(updatedAuthor, user, BETTY_SECOND_PICTURE_TITLE);

        createCommentary(spring, user, BETTY_FIRST_COMMENT);
        createCommentary(spring, user, BETTY_SECOND_COMMENT);
        createCommentary(spring, user, "Enjoy");
        createCommentary(summer, user, "I love summer!");
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

    private Picture createPicture(Author author, UserData userData, String title) {
        Picture picture = DomainPicture.createWith()
                .author(author)
                .path(FAKE_PICTURE_PATH)
                .userLogin(userData.getLogin())
                .title(title)
                .build();

        final long pictureID = pictureService.addPicture(picture);
        return DomainPicture.mergeID(pictureID, picture);
    }

    private long createCommentary(Picture picture, UserData userData, String comment) {
        Commentary commentary = DomainCommentary.createWith()
                .comment(comment)
                .userLogin(userData.getLogin())
                .picture(picture)
                .build();
        return commentaryService.addCommentary(picture.getId(), commentary);
    }
}
