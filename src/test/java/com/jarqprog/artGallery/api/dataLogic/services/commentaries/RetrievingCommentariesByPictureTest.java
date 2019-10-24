package com.jarqprog.artGallery.api.dataLogic.services.commentaries;


import com.jarqprog.artGallery.SpringServiceTestConfig;
import com.jarqprog.artGallery.api.dataLogic.useCases.CommentaryService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Values used in test methods are related to values set in InitialDataLoader
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringServiceTestConfig.class)
@Rollback
@Transactional
class RetrievingCommentariesByPictureTest {

    private static final Logger logger = LoggerFactory.getLogger(RetrievingCommentariesByPictureTest.class);

    private final CommentaryService commentaryService;

    @Autowired
    public RetrievingCommentariesByPictureTest(CommentaryService commentaryService) {
        assert commentaryService != null;
        this.commentaryService = commentaryService;
    }

//    @Test
//    @DisplayName("should return empty list")
//    void checkCommentariesRelatedToNotExistingPicture() {
//
//        long notExistingPictureId = 10099;
//
//        List<CommentaryDTO> commentaries = commentaryService.getAllCommentariesByPicture(notExistingPictureId);
//
//        assertTrue(commentaries.isEmpty());
//    }
//
//    @Test
//    @DisplayName("should retrieve 3 commentaries, all commentaries should be related to same Picture")
//    void checkCommentariesRelatedToPicture() {
//
//        int expectedSize = 3;
//        long  existingCommentaryId = 1;
//
//        CommentaryDTO commentaryDTO = commentaryService.findCommentaryById(existingCommentaryId);
//        PictureDTO pictureDTO = commentaryDTO.getPicture();
//        long pictureId = pictureDTO.getId();
//
//        List<CommentaryDTO> commentaries = commentaryService.getAllCommentariesByPicture(pictureId);
//
//        assertEquals(expectedSize, commentaries.size());
//        assertTrue(commentaries.stream().map(CommentaryDTO::getPicture).allMatch(p -> p.getId() == pictureId));
//    }
//
//    @Test
//    @DisplayName("should retrieve 4 commentaries including added one")
//    void checkCommentariesAfterAddingOne() {
//
//        CommentaryDTO existingOne = commentaryService.findCommentaryById(1); //to get Picture and User from it
//
//        String comment = "I'm added";
//        CommentaryDTO commentaryToAdd = new CommentaryDTO();
//        commentaryToAdd.setComment(comment);
//        commentaryToAdd.setPicture(existingOne.getPicture());
//        commentaryToAdd.setUser(existingOne.getUser());
//
//        long pictureId = existingOne.getPicture().getId();
//
//        commentaryService.addCommentary(pictureId, commentaryToAdd);
//
//        int expectedSize = 4;
//
//        List<CommentaryDTO> commentaries = commentaryService.getAllCommentariesByPicture(pictureId);
//
//        assertEquals(expectedSize, commentaries.size());
//        assertTrue(commentaries.stream().anyMatch(c -> c.getComment().equals(comment)));
//        assertTrue(commentaries.stream().map(CommentaryDTO::getPicture).allMatch(p -> p.getId() == pictureId));
//    }
//
//    @Test
//    @DisplayName("should retrieve 2 commentaries")
//    void checkCommentariesAfterRemovingOneOfThem() {
//
//        int expectedSize = 2;
//        long  existingCommentaryId = 1;
//
//        CommentaryDTO commentaryDTO = commentaryService.findCommentaryById(existingCommentaryId);
//        PictureDTO pictureDTO = commentaryDTO.getPicture();
//        long pictureId = pictureDTO.getId();
//
//        commentaryService.removeCommentary(existingCommentaryId);
//
//        List<CommentaryDTO> commentaries = commentaryService.getAllCommentariesByPicture(pictureId);
//
//        assertEquals(expectedSize, commentaries.size());
//        assertTrue(commentaries.stream().map(CommentaryDTO::getPicture).allMatch(p -> p.getId() == pictureId));
//    }
}
