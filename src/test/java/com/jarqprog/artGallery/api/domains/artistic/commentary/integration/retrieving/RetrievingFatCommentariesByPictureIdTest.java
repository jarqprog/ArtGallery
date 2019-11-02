package com.jarqprog.artGallery.api.domains.artistic.commentary.integration.retrieving;


import com.jarqprog.artGallery.api.ApiConstants;
import com.jarqprog.artGallery.api.SpringServiceTestConfig;
import com.jarqprog.artGallery.api.domains.artistic.commentary.CommentaryService;
import com.jarqprog.artGallery.api.domains.artistic.commentary.dto.CommentaryFat;
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

import java.util.List;

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
class RetrievingFatCommentariesByPictureIdTest {

    private static final Class<CommentaryFat> FAT_CLAZZ = CommentaryFat.class;

    private final CommentaryService commentaryService;

    @Autowired
    public RetrievingFatCommentariesByPictureIdTest(CommentaryService commentaryService) {
        assert commentaryService != null;
        this.commentaryService = commentaryService;
    }

    @Test
    @DisplayName("should return empty list")
    void checkCommentariesRelatedToNotExistingPicture() {

        long notExistingPictureId = 10099;

        List<? extends Commentary> commentaries = commentaryService
                .getAllCommentariesByPicture(notExistingPictureId, FAT_CLAZZ);

        assertTrue(commentaries.isEmpty());
    }

    @Test
    @DisplayName("should return commentaries in 'fat' format with valida data")
    void retrieveFatCommentaries() {

        final long pictureId = 1;
        final int expectedSize = InitialDataLoader.FIRST_PICTURE_COMMENTARIES_QUANTITY;

        List<? extends Commentary> commentaries = commentaryService
                .getAllCommentariesByPicture(pictureId, FAT_CLAZZ);

        assertEquals(expectedSize, commentaries.size());
        assertTrue(commentaries.stream().allMatch(c -> c.getPictureId() == pictureId));

        for (final Commentary commentary : commentaries) {
            final Picture picture = commentary.getPicture();
            final Author author = picture.getAuthor();
            assertNotNull(commentary);
            assertNotNull(picture);
            assertTrue(picture::hasAuthor);
            assertNotNull(author);
            assertEquals(pictureId, commentary.getPictureId());
            assertEquals(InitialDataLoader.BETTY_LOGIN, commentary.getUserLogin());
            assertEquals(pictureId, picture.getId());
            assertEquals(InitialDataLoader.FAKE_PICTURE_PATH, picture.getPath());
            assertEquals(InitialDataLoader.BETTY_FIRST_PICTURE_TITLE, picture.getTitle());
            assertEquals(InitialDataLoader.BETTY_LOGIN, picture.getUserLogin());
            assertEquals(InitialDataLoader.BETTY_ARTIST_NICK, author.getArtisticNickname());
        }
    }
}
