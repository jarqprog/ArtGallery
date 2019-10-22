package com.jarqprog.artGallery.springData.services.commentaries;


import com.jarqprog.artGallery.SpringServiceTestConfig;
import com.jarqprog.artGallery.domain.dto.CommentaryDTO;
import com.jarqprog.artGallery.domain.dto.PictureDTO;
import com.jarqprog.artGallery.domain.dto.UserDTO;
import com.jarqprog.artGallery.domain.useCases.CommentaryService;
import com.jarqprog.artGallery.springData.exceptions.InvalidObjectException;
import com.jarqprog.artGallery.springData.exceptions.ResourceAlreadyExists;
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
class AddingCommentaryTest {

    private final CommentaryService commentaryService;

    @Autowired
    public AddingCommentaryTest(CommentaryService commentaryService) {
        assert commentaryService != null;
        this.commentaryService = commentaryService;
    }

    @Test
    @DisplayName("InvalidObjectException should be thrown")
    void tryToAddCommentaryWithoutUser() {

        CommentaryDTO existingCommentary = commentaryService.findCommentaryById(1);
        PictureDTO pictureDTO = existingCommentary.getPicture();
        long pictureId = pictureDTO.getId();

        CommentaryDTO invalidCommentary = new CommentaryDTO();
        invalidCommentary.setComment("I am invalid - have no User");
        invalidCommentary.setPicture(pictureDTO);

        assertThrows(InvalidObjectException.class,
                () -> commentaryService.addCommentary(pictureId, invalidCommentary));
    }

    @Test
    @DisplayName("ResourceNotFoundException should be thrown")
    void tryToAddCommentaryUsingInvalidPictureId() {

        long notExistingPictureId = 87676;

        CommentaryDTO invalidCommentary = new CommentaryDTO();
        invalidCommentary.setComment("I am invalid - invalid picture id, no picture, no user");

        assertThrows(ResourceNotFoundException.class,
                () -> commentaryService.addCommentary(notExistingPictureId, invalidCommentary));
    }

    @Test
    @DisplayName("ResourceNotFoundException should be thrown")
    void tryToAddCommentaryWithoutPictureAndUser() {

        long notExistingPictureId = 87676;

        CommentaryDTO invalidCommentary = new CommentaryDTO();
        invalidCommentary.setComment("I am invalid - invalid picture id, no picture, no user");

        assertThrows(ResourceNotFoundException.class,
                () -> commentaryService.addCommentary(notExistingPictureId, invalidCommentary));;
    }

    @Test
    @DisplayName("ResourceAlreadyExists should be thrown")
    void tryToAddAlreadyExistingCommentary() {

        CommentaryDTO existingCommentary = commentaryService.findCommentaryById(1);
        PictureDTO pictureDTO = existingCommentary.getPicture();
        long pictureId = pictureDTO.getId();

        CommentaryDTO commentaryWithIdThatAlreadyExists = new CommentaryDTO();
        commentaryWithIdThatAlreadyExists.setId(existingCommentary.getId());
        commentaryWithIdThatAlreadyExists.setComment("I should not be persisted");
        commentaryWithIdThatAlreadyExists.setPicture(pictureDTO);
        commentaryWithIdThatAlreadyExists.setUser(existingCommentary.getUser());

        assertThrows(ResourceAlreadyExists.class,
                () -> commentaryService.addCommentary(pictureId, commentaryWithIdThatAlreadyExists));
    }

    @Test
    @DisplayName("Commentary should be added, values should match")
    void addValidCommentaryObject() {

        CommentaryDTO existingCommentary = commentaryService.findCommentaryById(1);
        PictureDTO pictureDTO = existingCommentary.getPicture();
        UserDTO userDTO = existingCommentary.getUser();
        long pictureId = pictureDTO.getId();
        String comment = "I am OK";

        CommentaryDTO commentaryToAdd = new CommentaryDTO();
        commentaryToAdd.setComment(comment);
        commentaryToAdd.setPicture(pictureDTO);
        commentaryToAdd.setUser(userDTO);

        CommentaryDTO addedCommentary = commentaryService.addCommentary(pictureId, commentaryToAdd);
        long addedCommentaryId = addedCommentary.getId();

        assertAll(
            () -> assertTrue(addedCommentaryId > 0),
            () -> assertEquals(comment, addedCommentary.getComment()),
            () -> assertEquals(pictureDTO.getId(), addedCommentary.getPicture().getId()),
            () -> assertEquals(userDTO.getId(), addedCommentary.getUser().getId())
        );

        CommentaryDTO retrievedCommentary = commentaryService.findCommentaryById(addedCommentaryId);

        assertAll(
                () -> assertEquals(addedCommentaryId, retrievedCommentary.getId()),
                () -> assertEquals(comment, retrievedCommentary.getComment()),
                () -> assertEquals(pictureDTO.getId(), retrievedCommentary.getPicture().getId()),
                () -> assertEquals(userDTO.getId(), retrievedCommentary.getUser().getId())
        );

    }

}
