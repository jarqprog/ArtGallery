package com.jarqprog.artGallery.springData.services.commentaries;


import com.jarqprog.artGallery.SpringServiceTestConfig;

import com.jarqprog.artGallery.domain.dto.CommentaryDTO;
import com.jarqprog.artGallery.domain.useCases.CommentaryService;
import com.jarqprog.artGallery.springData.repository.CommentaryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Values used in test methods are related to values set in InitialDataLoader
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringServiceTestConfig.class)
@Rollback
@Transactional
public class RetrievingCommentaryByIdTest {

    private final CommentaryService commentaryService;
//    private final CommentaryRepository commentaryRepository;

    @Autowired
    public RetrievingCommentaryByIdTest(CommentaryService commentaryService) {
        assert commentaryService != null;
        this.commentaryService = commentaryService;
    }

//    @Test
    @DisplayName("should return empty list")
    void checkCommentariesRelatedToNotExistingPicture() {

        long notExistingPictureId = 10099;

        List<CommentaryDTO> commentaries = commentaryService.getAllCommentariesByPicture(notExistingPictureId);

        assertTrue(commentaries.isEmpty());
    }
}
