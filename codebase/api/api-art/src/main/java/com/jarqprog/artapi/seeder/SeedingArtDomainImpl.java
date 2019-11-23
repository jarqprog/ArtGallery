package com.jarqprog.artapi.seeder;


import com.jarqprog.artapi.domains.author.AuthorService;
import com.jarqprog.artapi.domains.commentary.CommentaryService;
import com.jarqprog.artapi.domains.picture.PictureService;
import com.jarqprog.artdomain.model.author.Author;
import com.jarqprog.artdomain.model.author.DomainAuthor;
import com.jarqprog.artdomain.model.commentary.Commentary;
import com.jarqprog.artdomain.model.commentary.DomainCommentary;
import com.jarqprog.artdomain.model.picture.DomainPicture;
import com.jarqprog.artdomain.model.picture.Picture;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.jarqprog.commonapi.constants.seeder.ArtsDomainInitData.*;
import static com.jarqprog.commonapi.constants.seeder.PersonDomainInitData.BETTY_ID;
import static com.jarqprog.commonapi.constants.seeder.PersonDomainInitData.BETTY_LOGIN;


@Component
public class SeedingArtDomainImpl implements SeedingArtDomain {

    private static final Logger logger = LoggerFactory.getLogger(SeedingArtDomainImpl.class);

    @NonNull private final AuthorService authorService;
    @NonNull private final CommentaryService commentaryService;
    @NonNull private final PictureService pictureService;

    @Autowired
    public SeedingArtDomainImpl(@NonNull AuthorService authorService, @NonNull CommentaryService commentaryService, @NonNull PictureService pictureService) {
        this.authorService = authorService;
        this.commentaryService = commentaryService;
        this.pictureService = pictureService;
    }

    @Override
    @Transactional
    public void populateDb() {
        initBettyArts();
    }

    private void initBettyArts() {
        Author author = DomainAuthor.createWith()
                .artisticNickname(BETTY_ARTIST_NICK)
                .contactId(BETTY_ID)
                .build();

        final long authorId = authorService.addAuthor(author);
        logger.info("AuthorID: {}", authorId);
        Author updatedAuthor = DomainAuthor.mergeID(authorId, author);
        logger.info("Author: {}", updatedAuthor);

        Picture spring = createPicture(updatedAuthor, BETTY_LOGIN, BETTY_FIRST_PICTURE_TITLE);
        Picture summer = createPicture(updatedAuthor, BETTY_LOGIN, BETTY_SECOND_PICTURE_TITLE);

        createCommentary(spring, BETTY_LOGIN, BETTY_FIRST_COMMENT);
        createCommentary(spring, BETTY_LOGIN, BETTY_SECOND_COMMENT);
        createCommentary(spring, BETTY_LOGIN, "Enjoy");
        createCommentary(summer, BETTY_LOGIN, "I love summer!");

    }

    private Picture createPicture(Author author, String userLogin, String title) {
        Picture picture = DomainPicture.createWith()
                .author(author)
                .path(FAKE_PICTURE_PATH)
                .userLogin(userLogin)
                .title(title)
                .build();

        final long pictureID = pictureService.addPicture(picture);
        return DomainPicture.mergeID(pictureID, picture);
    }

    private long createCommentary(Picture picture, String userLogin, String comment) {
        Commentary commentary = DomainCommentary.createWith()
                .comment(comment)
                .userLogin(userLogin)
                .picture(picture)
                .build();
        return commentaryService.addCommentary(picture.getId(), commentary);
    }
}
