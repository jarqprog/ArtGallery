package com.jarqprog.artGallery.api.domains.artistic.commentary.integration.adding;


import com.jarqprog.artGallery.api.ApiConstants;
import com.jarqprog.artGallery.api.SpringServiceTestConfig;
import com.jarqprog.artGallery.api.domains.artistic.commentary.CommentaryService;
import com.jarqprog.artGallery.api.domains.artistic.commentary.dto.CommentaryDTO;
import com.jarqprog.artGallery.api.domains.artistic.commentary.dto.CommentaryThin;
import com.jarqprog.artGallery.api.domains.exceptions.ResourceAlreadyExists;
import com.jarqprog.artGallery.api.domains.exceptions.ResourceNotFoundException;
import lombok.NonNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.io.InvalidObjectException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Values used in test methods are related to values set in InitialDataLoader
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringServiceTestConfig.class)
@Rollback
@Transactional
@EnabledIfSystemProperty(named = "spring.profiles.active", matches = ApiConstants.TEST_PROFILE)
class AddingCommentaryTest {

    @NonNull
    private final CommentaryService commentaryService;

    @Autowired
    public AddingCommentaryTest(@NonNull CommentaryService commentaryService) {
        this.commentaryService = commentaryService;
    }

    @Test
    @DisplayName("IllegalArgumentException should be thrown")
    void tryToAddCommentaryWithoutUser() {

        long pictureId = 1;
        CommentaryDTO invalidCommentary = CommentaryThin.createWith()
                .comment("a")
                .pictureId(pictureId)
                .build();

        assertThrows(IllegalArgumentException.class,
                () -> commentaryService.addCommentary(pictureId, invalidCommentary));
    }

    @Test
    @DisplayName("ResourceNotFoundException should be thrown")
    void tryToAddCommentaryUsingInvalidPictureId() {

        long pictureId = 1001;
        CommentaryDTO invalidCommentary = CommentaryThin.createWith()
                .comment("a")
                .userLogin("login")
                .pictureId(pictureId)
                .build();

        assertThrows(ResourceNotFoundException.class,
                () -> commentaryService.addCommentary(pictureId, invalidCommentary));
    }

    @Test
    @DisplayName("IllegalArgumentException should be thrown")
    void tryToAddCommentaryIfProvidedPictureIdIsDifferentThanCommentaryPictureId() {

        long pictureId = 1;
        long providedPictureId = 2;
        CommentaryDTO invalidCommentary = CommentaryThin.createWith()
                .comment("a")
                .userLogin("login")
                .pictureId(pictureId)
                .build();

        assertThrows(IllegalArgumentException.class,
                () -> commentaryService.addCommentary(providedPictureId, invalidCommentary));
    }

    @Test
    @DisplayName("IllegalArgumentException should be thrown")
    void tryToAddCommentaryWithoutPicture() {

        long providedPictureId = 2002;
        CommentaryDTO invalidCommentary = CommentaryThin.createWith()
                .comment("a")
                .userLogin("login")
                .build();

        assertThrows(IllegalArgumentException.class,
                () -> commentaryService.addCommentary(providedPictureId, invalidCommentary));
    }

    @Test
    @DisplayName("ResourceAlreadyExists should be thrown")
    void tryToAddAlreadyExistingCommentary() {

        long existingId = 1;
        long pictureId = 1;
        CommentaryThin invalidCommentary = CommentaryThin.createWith()
                .comment("a")
                .userLogin("login")
                .pictureId(pictureId)
                .build();

        invalidCommentary.setId(existingId);

        assertThrows(ResourceAlreadyExists.class,
                () -> commentaryService.addCommentary(pictureId, invalidCommentary));
    }

    @Test
    @DisplayName("Commentary should be added, values should match")
    void addValidCommentary() {

        final String comment = "a";
        final long pictureId = 1;
        final String login = "login";

        CommentaryThin commentary = CommentaryThin.createWith()
                .comment(comment)
                .userLogin(login)
                .pictureId(pictureId)
                .build();

        long commentaryID = commentaryService.addCommentary(pictureId, commentary);
        CommentaryDTO addedCommentary = commentaryService.findCommentaryById(commentaryID);

        assertAll(
            () -> assertTrue(commentaryID > 0),
            () -> assertEquals(comment, addedCommentary.getComment()),
            () -> assertEquals(pictureId, addedCommentary.getPictureId()),
            () -> assertEquals(login, addedCommentary.getUserLogin())
        );
    }
}
