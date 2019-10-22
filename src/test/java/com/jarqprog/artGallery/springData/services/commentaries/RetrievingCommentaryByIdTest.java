package com.jarqprog.artGallery.springData.services.commentaries;


import com.jarqprog.artGallery.SpringServiceTestConfig;

import com.jarqprog.artGallery.domain.dto.CommentaryDTO;
import com.jarqprog.artGallery.domain.useCases.CommentaryService;
import com.jarqprog.artGallery.springData.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Values used in test methods are related to values set in InitialDataLoader
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringServiceTestConfig.class)
@Rollback
@Transactional
class RetrievingCommentaryByIdTest {

    private final CommentaryService commentaryService;

    @Autowired
    public RetrievingCommentaryByIdTest(CommentaryService commentaryService) {
        assert commentaryService != null;
        this.commentaryService = commentaryService;
    }

    @Test
    @DisplayName("ResourceNotFoundException should be thrown")
    void tryToRetrieveNotExistingComment() {

        long notExistingCommentaryId = 10099;
        long pictureId = 1;

        assertThrows(ResourceNotFoundException.class,
                () -> commentaryService.findCommentaryById(pictureId, notExistingCommentaryId));
    }

    @Test
    @DisplayName("ResourceNotFoundException should be thrown")
    void tryToRetrieveCommentIfPictureIdIsInvalid() {

        long commentaryId = 1;
        long notExistingPictureId = 1876876;

        assertThrows(ResourceNotFoundException.class,
                () -> commentaryService.findCommentaryById(notExistingPictureId, commentaryId));
    }

    @Test
    @DisplayName("should return valid comment")
    void retrieveComment() {

        long existingCommentaryId = 1;
        long pictureId = 1;
        String comment = "This is my first painting";
        long userId = 3;

        CommentaryDTO commentaryDTO = commentaryService.findCommentaryById(pictureId, existingCommentaryId);

        assertAll(

                () -> assertEquals(existingCommentaryId, commentaryDTO.getId()),
                () -> assertEquals(comment, commentaryDTO.getComment()),
                () -> assertEquals(pictureId, commentaryDTO.getPicture().getId()),
                () -> assertEquals(userId, commentaryDTO.getUser().getId())
        );
    }
}
