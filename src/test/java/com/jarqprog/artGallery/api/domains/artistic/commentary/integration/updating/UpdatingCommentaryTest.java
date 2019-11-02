package com.jarqprog.artGallery.api.domains.artistic.commentary.integration.updating;


import com.jarqprog.artGallery.api.SpringServiceTestConfig;
import com.jarqprog.artGallery.api.domains.artistic.commentary.CommentaryService;

import com.jarqprog.artGallery.api.domains.artistic.commentary.dto.CommentaryDTO;
import com.jarqprog.artGallery.api.domains.artistic.commentary.dto.CommentaryThin;
import com.jarqprog.artGallery.api.domains.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.api.infrastructure.components.dataLoader.InitialDataLoader;
import lombok.NonNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static com.jarqprog.artGallery.api.SpringServiceTestConfig.INTEGRATION_TEST_REGEX;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Values used in test methods are related to values set in InitialDataLoader
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringServiceTestConfig.class)
@Rollback
@Transactional
@EnabledIfSystemProperty(named = "spring.profiles.active", matches = INTEGRATION_TEST_REGEX)
class UpdatingCommentaryTest {

    private static final Logger logger = LoggerFactory.getLogger(UpdatingCommentaryTest.class);

    private final CommentaryService commentaryService;

    @Autowired
    public UpdatingCommentaryTest(@NonNull CommentaryService commentaryService) {
        this.commentaryService = commentaryService;
    }

    @Test
    @DisplayName("IllegalArgumentException should be thrown")
    void tryToUpdateNotExistingCommentary() {

        final long commentaryId = 76576;
        final long pictureId = 1;

        CommentaryDTO notExistingCommentary = CommentaryThin.createWith()
                .id(commentaryId)
                .comment("I am not in DB")
                .pictureId(pictureId)
                .userLogin(InitialDataLoader.BETTY_LOGIN)
                .build();

        assertThrows(IllegalArgumentException.class,
                () -> commentaryService.updateCommentary(pictureId, commentaryId, notExistingCommentary));
    }

    @Test
    @DisplayName("IllegalArgumentException should be thrown")
    void tryToUpdateExistingCommentaryUsingInvalidPictureId_pictureIdProvidedInCommentary() {

        final long existingPictureId = 1;
        final long notExistingPictureId = 76576;
        final long commentaryId = 1;

        CommentaryDTO commentary = CommentaryThin.createWith()
                .id(commentaryId)
                .comment(InitialDataLoader.BETTY_FIRST_COMMENT)
                .pictureId(notExistingPictureId)
                .userLogin(InitialDataLoader.BETTY_LOGIN)
                .build();


        assertThrows(IllegalArgumentException.class,
                () -> commentaryService.updateCommentary(existingPictureId, commentaryId, commentary));
    }

    @Test
    @DisplayName("IllegalArgumentException should be thrown")
    void tryToUpdateExistingCommentaryUsingInvalidPictureId_pictureIdProvidedAsArgument() {

        final long existingPictureId = 1;
        final long notExistingPictureId = 76576;
        final long commentaryId = 1;

        CommentaryDTO commentary = CommentaryThin.createWith()
                .id(commentaryId)
                .comment(InitialDataLoader.BETTY_FIRST_COMMENT)
                .pictureId(existingPictureId)
                .userLogin(InitialDataLoader.BETTY_LOGIN)
                .build();


        assertThrows(IllegalArgumentException.class,
                () -> commentaryService.updateCommentary(notExistingPictureId, commentaryId, commentary));
    }

    @Test
    @DisplayName("Commentary should not be updated, IllegalArgumentException should be thrown")
    void tryToChangePictureId() {

        final long pictureId = 1;
        final long newPictureId = 2;
        final long commentaryId = 1;

        CommentaryDTO commentary = CommentaryThin.createWith()
                .id(commentaryId)
                .comment(InitialDataLoader.BETTY_FIRST_COMMENT)
                .pictureId(newPictureId)
                .userLogin(InitialDataLoader.BETTY_LOGIN)
                .build();

        assertThrows(IllegalArgumentException.class,
                () -> commentaryService.updateCommentary(pictureId, commentaryId, commentary));

        final CommentaryDTO updatedCommentary = commentaryService.findCommentaryById(commentaryId);

        assertAll(
                () -> assertEquals(commentaryId, updatedCommentary.getId()),
                () -> assertEquals(InitialDataLoader.BETTY_FIRST_COMMENT, updatedCommentary.getComment()),
                () -> assertEquals(pictureId, updatedCommentary.getPictureId()),
                () -> assertEquals(InitialDataLoader.BETTY_LOGIN, updatedCommentary.getUserLogin())
        );
    }

    @Test
    @DisplayName("Commentary should not be updated, IllegalArgumentException should be thrown")
    void tryToChangeUserLogin() {

        final long pictureId = 1;
        final long commentaryId = 1;
        final String newUserLogin = "newLogin";

        CommentaryDTO commentary = CommentaryThin.createWith()
                .id(commentaryId)
                .comment(InitialDataLoader.BETTY_FIRST_COMMENT)
                .pictureId(pictureId)
                .userLogin(newUserLogin)
                .build();

        assertThrows(IllegalArgumentException.class,
                () -> commentaryService.updateCommentary(pictureId, commentaryId, commentary));

        final CommentaryDTO updatedCommentary = commentaryService.findCommentaryById(commentaryId);

        assertAll(
                () -> assertEquals(commentaryId, updatedCommentary.getId()),
                () -> assertEquals(InitialDataLoader.BETTY_FIRST_COMMENT, updatedCommentary.getComment()),
                () -> assertEquals(pictureId, updatedCommentary.getPictureId()),
                () -> assertEquals(InitialDataLoader.BETTY_LOGIN, updatedCommentary.getUserLogin())
        );
    }

    @Test
    @DisplayName("Commentary should not be updated, IllegalArgumentException should be thrown")
    void tryToUpdateExistingCommentaryWithoutUserLogin() {

        final long pictureId = 1;
        final long commentaryId = 1;

        CommentaryDTO commentary = CommentaryThin.createWith()
                .id(commentaryId)
                .comment(InitialDataLoader.BETTY_FIRST_COMMENT)
                .pictureId(pictureId)
                .userLogin(null)
                .build();

        assertThrows(IllegalArgumentException.class,
                () -> commentaryService.updateCommentary(pictureId, commentaryId, commentary));

        final CommentaryDTO updatedCommentary = commentaryService.findCommentaryById(commentaryId);

        assertAll(
                () -> assertEquals(commentaryId, updatedCommentary.getId()),
                () -> assertEquals(InitialDataLoader.BETTY_FIRST_COMMENT, updatedCommentary.getComment()),
                () -> assertEquals(pictureId, updatedCommentary.getPictureId()),
                () -> assertEquals(InitialDataLoader.BETTY_LOGIN, updatedCommentary.getUserLogin())
        );
    }

    @Test
    @DisplayName("Commentary should not be updated, IllegalArgumentException should be thrown")
    void tryToUpdateExistingCommentaryWithEmptyComment() {

        final long pictureId = 1;
        final long commentaryId = 1;
        final String editedComment = "";

        CommentaryDTO commentary = CommentaryThin.createWith()
                .id(commentaryId)
                .comment(editedComment)
                .pictureId(pictureId)
                .userLogin(InitialDataLoader.BETTY_LOGIN)
                .build();

        assertThrows(IllegalArgumentException.class,
                () -> commentaryService.updateCommentary(pictureId, commentaryId, commentary));

        final CommentaryDTO retrievedCommentary = commentaryService.findCommentaryById(commentaryId);

        assertAll(
                () -> assertEquals(commentaryId, retrievedCommentary.getId()),
                () -> assertEquals(InitialDataLoader.BETTY_FIRST_COMMENT, retrievedCommentary.getComment()),
                () -> assertEquals(pictureId, retrievedCommentary.getPictureId()),
                () -> assertEquals(InitialDataLoader.BETTY_LOGIN, retrievedCommentary.getUserLogin())
        );
    }

    @Test
    @DisplayName("Commentary should be properly updated")
    void tryToChangeComment() {

        final long pictureId = 1;
        final long commentaryId = 1;
        final String editedComment = "Oh la la!";

        CommentaryDTO commentary = CommentaryThin.createWith()
                .id(commentaryId)
                .comment(editedComment)
                .pictureId(pictureId)
                .userLogin(InitialDataLoader.BETTY_LOGIN)
                .build();


        commentaryService.updateCommentary(pictureId, commentaryId, commentary);

        final CommentaryDTO updatedCommentary = commentaryService.findCommentaryById(commentaryId);

        assertAll(
                () -> assertEquals(commentaryId, updatedCommentary.getId()),
                () -> assertEquals(editedComment, updatedCommentary.getComment()),
                () -> assertEquals(pictureId, updatedCommentary.getPictureId()),
                () -> assertEquals(InitialDataLoader.BETTY_LOGIN, updatedCommentary.getUserLogin())
        );
    }
}
