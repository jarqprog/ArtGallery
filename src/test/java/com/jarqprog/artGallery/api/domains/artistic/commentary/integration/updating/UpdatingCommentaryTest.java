package com.jarqprog.artGallery.api.domains.artistic.commentary.integration.updating;


import com.jarqprog.artGallery.api.SpringServiceTestConfig;
import com.jarqprog.artGallery.api.domains.artistic.commentary.CommentaryService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Values used in test methods are related to values set in InitialDataLoader
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringServiceTestConfig.class)
@Rollback
@Transactional
class UpdatingCommentaryTest {

    private final CommentaryService commentaryService;

    @Autowired
    public UpdatingCommentaryTest(CommentaryService commentaryService) {
        assert commentaryService != null;
        this.commentaryService = commentaryService;
    }
//
//    @Test
//    @DisplayName("ResourceNotFoundException should be thrown")
//    void tryToUpdateNotExistingCommentary() {
//
//        long notExistingCommentaryId = 76576;
//
//        CommentaryDTO existingCommentary = commentaryService.findCommentaryById(1);
//        PictureDTO pictureDTO = existingCommentary.getPicture();
//        long pictureId = pictureDTO.getId();
//
//        CommentaryDTO invalidCommentary = new CommentaryDTO();
//        invalidCommentary.setId(notExistingCommentaryId);
//        invalidCommentary.setComment("I am not in DB");
//        invalidCommentary.setPicture(pictureDTO);
//        invalidCommentary.setUser(existingCommentary.getUser());
//
//        assertThrows(ResourceNotFoundException.class,
//                () -> commentaryService.updateCommentary(pictureId, notExistingCommentaryId, invalidCommentary));
//    }
//
//    @Test
//    @DisplayName("InvalidObjectException should be thrown")
//    void tryToUpdateExistingCommentaryUsingInvalidPictureId() {
//
//        long notExistingPictureId = 76576;
//
//        CommentaryDTO existingCommentary = commentaryService.findCommentaryById(1);
//        existingCommentary.setComment("changed");
//
//        long commentaryId = existingCommentary.getId();
//
//        assertThrows(InvalidDataException.class,
//                () -> commentaryService.updateCommentary(notExistingPictureId, commentaryId, existingCommentary));
//    }
//
//    @Test
//    @DisplayName("InvalidObjectException should be thrown")
//    void tryToUpdateExistingCommentaryWithoutUser() {
//
//        CommentaryDTO existingCommentary = commentaryService.findCommentaryById(1);
//        PictureDTO pictureDTO = existingCommentary.getPicture();
//        long pictureId = pictureDTO.getId();
//        long commentaryId = existingCommentary.getId();
//
//        existingCommentary.setUser(null);
//
//        assertThrows(InvalidDataException.class,
//                () -> commentaryService.updateCommentary(pictureId, commentaryId, existingCommentary));
//    }
//
//    @Test
//    @DisplayName("InvalidObjectException should be thrown - user has invalid Id")
//    void tryToUpdateExistingCommentaryWithInvalidUser() {
//
//        CommentaryDTO existingCommentary = commentaryService.findCommentaryById(1);
//        PictureDTO pictureDTO = existingCommentary.getPicture();
//        UserDTO userDTO = existingCommentary.getUser();
//        long pictureId = pictureDTO.getId();
//        long commentaryId = existingCommentary.getId();
//
//        userDTO.setId(-10);
//
//        assertThrows(InvalidDataException.class,
//                () -> commentaryService.updateCommentary(pictureId, commentaryId, existingCommentary));
//    }


}
