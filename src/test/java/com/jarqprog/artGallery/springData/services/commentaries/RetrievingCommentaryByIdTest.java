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
public class RetrievingCommentaryByIdTest {

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

        assertThrows(ResourceNotFoundException.class,
                () -> commentaryService.findCommentaryById(notExistingCommentaryId));
    }

    @Test
    @DisplayName("comment should be returned with valid values")
    void tryToRetrieveExistingComment() {

        long existingCommentaryId = 1;
        String comment = "This is my first painting";

        CommentaryDTO commentaryDTO = commentaryService.findCommentaryById(existingCommentaryId);

        assertAll(

                () -> assertEquals(existingCommentaryId, commentaryDTO.getId()),
                () -> assertEquals(comment, commentaryDTO.getComment())



        );
    }


//    id: 1,
//    version: 0,
//    content: "This is my first painting",
//    user: {
//        id: 3,
//                version: 0,
//                contact: {
//            id: 3,
//                    version: 0,
//                    firstName: "Betty",
//                    lastName: "Sue",
//                    nickname: "betty80",
//                    email: "bettys@gmail.com"
//        },
//        login: "betty80"
//    },
//    picture: {
//        id: 1,
//                version: 0,
//                title: "Spring",
//                path: null,
//                author: {
//            id: 1,
//                    version: 0,
//                    artisticNickname: "betty-artist",
//                    contact: {
//                id: 3,
//                        version: 0,
//                        firstName: "Betty",
//                        lastName: "Sue",
//                        nickname: "betty80",
//                        email: "bettys@gmail.com"
//            }
//        },
//        user: {
//            id: 3,
//                    version: 0,
//                    contact: {
//                id: 3,
//                        version: 0,
//                        firstName: "Betty",
//                        lastName: "Sue",
//                        nickname: "betty80",
//                        email: "bettys@gmail.com"
//            },
//            login: "betty80"
}
