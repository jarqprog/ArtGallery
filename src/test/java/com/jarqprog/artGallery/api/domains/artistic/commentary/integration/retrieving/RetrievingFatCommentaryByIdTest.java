package com.jarqprog.artGallery.api.domains.artistic.commentary.integration.retrieving;


import com.jarqprog.artGallery.api.SpringServiceTestConfig;
import com.jarqprog.artGallery.api.domains.artistic.commentary.CommentaryService;

import com.jarqprog.artGallery.api.domains.artistic.commentary.dto.CommentaryFat;
import com.jarqprog.artGallery.api.domains.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.api.infrastructure.components.dataLoader.InitialDataLoader;
import com.jarqprog.artGallery.domain.artistic.Author;
import com.jarqprog.artGallery.domain.artistic.Commentary;
import com.jarqprog.artGallery.domain.artistic.Picture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static com.jarqprog.artGallery.api.SpringServiceTestConfig.INTEGRATION_TEST_REGEX;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Values used in test methods are related to values set in InitialDataLoader
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringServiceTestConfig.class)
@Rollback
@Transactional
@EnabledIfSystemProperty(named = "spring.profiles.active", matches = INTEGRATION_TEST_REGEX)
class RetrievingFatCommentaryByIdTest {

    private static final Class<CommentaryFat> FAT_CLAZZ = CommentaryFat.class;

    private final CommentaryService commentaryService;

    @Autowired
    public RetrievingFatCommentaryByIdTest(CommentaryService commentaryService) {
        assert commentaryService != null;
        this.commentaryService = commentaryService;
    }

    @Test
    @DisplayName("ResourceNotFoundException should be thrown")
    void tryToRetrieveNotExistingComment() {

        long notExistingCommentaryId = 10099;

        assertThrows(ResourceNotFoundException.class,
                () -> commentaryService.findCommentaryById(notExistingCommentaryId, FAT_CLAZZ));
    }

    @Test
    @DisplayName("should return comment in 'fat' format with valida data")
    void retrieveFatComment() {

        final long existingCommentaryId = 1;
        final long pictureId = 1;

        final Commentary commentary = commentaryService.findCommentaryById(existingCommentaryId, FAT_CLAZZ);
        final Picture picture = commentary.getPicture();
        final Author author = picture.getAuthor();

        assertAll(
                () -> assertNotNull(commentary),
                () -> assertNotNull(picture),
                () -> assertTrue(picture::hasAuthor),
                () -> assertNotNull(author),
                () -> assertEquals(existingCommentaryId, commentary.getId()),
                () -> assertEquals(InitialDataLoader.BETTY_FIRST_COMMENT, commentary.getComment()),
                () -> assertEquals(pictureId, commentary.getPictureId()),
                () -> assertEquals(InitialDataLoader.BETTY_LOGIN, commentary.getUserLogin()),
                () -> assertEquals(pictureId, picture.getId()),
                () -> assertEquals(InitialDataLoader.FAKE_PICTURE_PATH, picture.getPath()),
                () -> assertEquals(InitialDataLoader.BETTY_FIRST_PICTURE_TITLE, picture.getTitle()),
                () -> assertEquals(InitialDataLoader.BETTY_LOGIN, picture.getUserLogin()),
                () -> assertEquals(InitialDataLoader.BETTY_ARTIST_NICK, author.getArtisticNickname())
        );
    }
}
