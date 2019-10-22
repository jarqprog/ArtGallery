package com.jarqprog.artGallery.springData.services.commentaries;

import com.jarqprog.artGallery.SpringServiceTestConfig;
import com.jarqprog.artGallery.domain.dto.CommentaryDTO;
import com.jarqprog.artGallery.domain.useCases.CommentaryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Values used in test methods are related to values set in InitialDataLoader
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringServiceTestConfig.class)
@Rollback
@Transactional
class RetrievingAllCommentariesAfterAddingTest {

    private final CommentaryService commentaryService;

    @Autowired
    public RetrievingAllCommentariesAfterAddingTest(CommentaryService commentaryService) {
        assert commentaryService != null;
        this.commentaryService = commentaryService;
    }

    @Test
    @DisplayName("should retrieve 5 commentaries including added one")
    void checkCommentariesAfterAddingOne() {

        CommentaryDTO existingOne = commentaryService.findCommentaryById(1L); //to get Picture and User from it

        String comment = "I'm added";
        CommentaryDTO commentaryToAdd = new CommentaryDTO();
        commentaryToAdd.setComment(comment);
        commentaryToAdd.setPicture(existingOne.getPicture());
        commentaryToAdd.setUser(existingOne.getUser());

        long pictureId = existingOne.getPicture().getId();

        commentaryService.addCommentary(pictureId, commentaryToAdd);

        int expectedSize = 5;

        List<CommentaryDTO> commentaries = commentaryService.getAllCommentaries();

        assertEquals(expectedSize, commentaries.size());
        assertTrue(commentaries.stream().anyMatch(c -> c.getComment().equals(comment)));
    }
}
