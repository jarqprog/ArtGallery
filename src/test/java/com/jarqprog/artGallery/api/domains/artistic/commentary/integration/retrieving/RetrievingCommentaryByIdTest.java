package com.jarqprog.artGallery.api.domains.artistic.commentary.integration.retrieving;


import com.jarqprog.artGallery.api.ApiConstants;
import com.jarqprog.artGallery.api.SpringServiceTestConfig;
import com.jarqprog.artGallery.api.domains.artistic.commentary.CommentaryService;
import com.jarqprog.artGallery.api.domains.artistic.commentary.dto.CommentaryDTO;
import com.jarqprog.artGallery.api.domains.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.api.infrastructure.components.dataLoader.InitialDataLoader;

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
class RetrievingCommentaryByIdTest {

    private final CommentaryService commentaryService;

    @Autowired
    public RetrievingCommentaryByIdTest(@NonNull CommentaryService commentaryService) {
        this.commentaryService = commentaryService;
    }

    @Test
    @DisplayName("ResourceNotFoundException should be thrown")
    void tryToRetrieveNotExistingComment() {

        long notExistingCommentaryId = 10099;

        assertThrows(ResourceNotFoundException.class,
                () -> commentaryService.findCommentaryById(notExistingCommentaryId));
    }

    @Test
    @DisplayName("should return valid comment")
    void retrieveComment() {

        final long existingCommentaryId = 1;
        final long pictureId = 1;

        CommentaryDTO commentaryDTO = commentaryService.findCommentaryById(existingCommentaryId);

        assertAll(
                () -> assertEquals(existingCommentaryId, commentaryDTO.getId()),
                () -> assertEquals(InitialDataLoader.BETTY_FIRST_COMMENT, commentaryDTO.getComment()),
                () -> assertEquals(pictureId, commentaryDTO.getPictureId()),
                () -> assertEquals(InitialDataLoader.BETTY_LOGIN, commentaryDTO.getUserLogin())
        );
    }
}
