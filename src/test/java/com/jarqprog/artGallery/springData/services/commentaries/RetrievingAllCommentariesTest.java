package com.jarqprog.artGallery.springData.services.commentaries;

import com.jarqprog.artGallery.SpringServiceTestConfig;
import com.jarqprog.artGallery.domain.dto.CommentaryDTO;
import com.jarqprog.artGallery.domain.useCases.CommentaryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringServiceTestConfig.class)
class RetrievingAllCommentariesTest {

    private static final Logger logger = LoggerFactory.getLogger(RetrievingAllCommentariesTest.class);

    private final CommentaryService commentaryService;

    @Autowired
    public RetrievingAllCommentariesTest(CommentaryService commentaryService) {
        this.commentaryService = commentaryService;
    }

    @Test
    @DisplayName("should retrieve 4 commentaries")
    void checkQuantityIsCorrect() {

        int expected = 4;

        List<CommentaryDTO> commentaries = commentaryService.getAllCommentaries();

        assertEquals(expected, commentaries.size());
    }

    @Test
    void findCommentaryById1() {
        commentaryService.findCommentaryById(1L);
    }

}