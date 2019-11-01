package com.jarqprog.artGallery.api.domains.artistic.commentary.integration.retrieving;


import com.jarqprog.artGallery.api.SpringServiceTestConfig;
import com.jarqprog.artGallery.api.domains.artistic.commentary.CommentaryService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Values used in test methods are related to values set in InitialDataLoader
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringServiceTestConfig.class)
@Rollback
@Transactional
class RetrievingCommentaryByIdAndPictureIdTest {

    private final CommentaryService commentaryService;

    @Autowired
    public RetrievingCommentaryByIdAndPictureIdTest(CommentaryService commentaryService) {
        assert commentaryService != null;
        this.commentaryService = commentaryService;
    }

//    @Test
//    @DisplayName("ResourceNotFoundException should be thrown")
//    void tryToRetrieveNotExistingComment() {
//
//        long notExistingCommentaryId = 10099;
//
//        assertThrows(ResourceNotFoundException.class,
//                () -> commentaryService.findCommentaryById(notExistingCommentaryId));
//    }
//
//    @Test
//    @DisplayName("comment should be returned with valid values")
//    void tryToRetrieveExistingComment() {
//
//        long existingCommentaryId = 1;
//        String comment = "This is my first painting";
//        long pictureId = 1;
//        long userId = 3;
//
//        CommentaryDTO commentaryDTO = commentaryService.findCommentaryById(existingCommentaryId);
//
//        assertAll(
//
//                () -> assertEquals(existingCommentaryId, commentaryDTO.getId()),
//                () -> assertEquals(comment, commentaryDTO.getComment()),
//                () -> assertEquals(pictureId, commentaryDTO.getPicture().getId()),
//                () -> assertEquals(userId, commentaryDTO.getUser().getId())
//        );
//    }

}
