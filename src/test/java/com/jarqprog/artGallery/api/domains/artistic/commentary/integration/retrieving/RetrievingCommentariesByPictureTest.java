package com.jarqprog.artGallery.api.domains.artistic.commentary.integration.retrieving;


import com.jarqprog.artGallery.api.ApiConstants;
import com.jarqprog.artGallery.api.SpringServiceTestConfig;
import com.jarqprog.artGallery.api.domains.artistic.commentary.CommentaryService;
import com.jarqprog.artGallery.api.domains.artistic.commentary.dto.CommentaryDTO;
import com.jarqprog.artGallery.api.domains.artistic.commentary.dto.CommentaryThin;
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

import java.util.List;

import static com.jarqprog.artGallery.api.SpringServiceTestConfig.INTEGRATION_TEST_REGEX;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Values used in test methods are related to values set in InitialDataLoader
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringServiceTestConfig.class)
@Rollback
@Transactional
@EnabledIfSystemProperty(named = "spring.profiles.active", matches = INTEGRATION_TEST_REGEX)
class RetrievingCommentariesByPictureTest {

    private static final Logger logger = LoggerFactory.getLogger(RetrievingCommentariesByPictureTest.class);

    private final CommentaryService commentaryService;

    @Autowired
    public RetrievingCommentariesByPictureTest(@NonNull CommentaryService commentaryService) {
        this.commentaryService = commentaryService;
    }

    @Test
    @DisplayName("should return empty list")
    void checkCommentariesRelatedToNotExistingPicture() {

        final long notExistingPictureId = 10099;

        List<CommentaryDTO> commentaries = commentaryService.getAllCommentariesByPicture(notExistingPictureId);

        assertTrue(commentaries.isEmpty());
    }

    @Test
    @DisplayName("should retrieve 3 commentaries, all commentaries should be related to same Picture")
    void checkCommentariesRelatedToPicture() {

        final int expectedSize = InitialDataLoader.FIRST_PICTURE_COMMENTARIES_QUANTITY;
        final long pictureId = 1;

        List<CommentaryDTO> commentaries = commentaryService.getAllCommentariesByPicture(pictureId);

        assertEquals(expectedSize, commentaries.size());
        assertTrue(commentaries.stream().allMatch(c -> c.getPictureId() == pictureId));
    }

    @Test
    @DisplayName("should retrieve 4 commentaries including added one")
    void checkCommentariesAfterAddingOne() {

        final String comment = "I'm added";
        final String userLogin = "login";
        final long pictureId = 1;
        final int expectedSize = 4;

        CommentaryDTO commentaryDTO = CommentaryThin.createWith()
                .comment(comment)
                .userLogin(userLogin)
                .pictureId(pictureId)
                .build();

        commentaryService.addCommentary(pictureId, commentaryDTO);

        List<CommentaryDTO> commentaries = commentaryService.getAllCommentariesByPicture(pictureId);

        assertEquals(expectedSize, commentaries.size());
        assertTrue(commentaries.stream().anyMatch(c -> c.getComment().equals(comment)));
        assertTrue(commentaries.stream().allMatch(c -> c.getPictureId() == pictureId));
    }

    @Test
    @DisplayName("should retrieve 2 commentaries")
    void checkCommentariesAfterRemovingOneOfThem() {

        final int expectedSize = 2;
        final long  existingCommentaryId = 1;
        final long pictureId = 1;

        commentaryService.removeCommentary(existingCommentaryId);

        List<CommentaryDTO> commentaries = commentaryService.getAllCommentariesByPicture(pictureId);

        assertEquals(expectedSize, commentaries.size());
        assertTrue(commentaries.stream().allMatch(c -> c.getPictureId() == pictureId));
    }
}
