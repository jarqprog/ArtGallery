package com.jarqprog.artGallery.springData.services.commentaries;

import com.jarqprog.artGallery.SpringServiceTestConfig;
import com.jarqprog.artGallery.domain.dto.CommentaryDTO;
import com.jarqprog.artGallery.domain.useCases.CommentaryService;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Values used in test methods are related to values set in InitialDataLoader
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringServiceTestConfig.class)
@Transactional
@Rollback
class RetrievingAllCommentariesTest {

    private static final Logger logger = LoggerFactory.getLogger(RetrievingAllCommentariesTest.class);

    private final List<CommentaryDTO> commentaries;

    @Autowired
    RetrievingAllCommentariesTest(CommentaryService commentaryService) {
        assert commentaryService != null;
        commentaries = commentaryService.getAllCommentaries();
    }

    @Test
    @DisplayName("should retrieve 4 commentaries")
    void checkQuantityIsCorrect() {

        int expected = 4;
        assertEquals(expected, commentaries.size());
    }

    @Test
    @DisplayName("every comment should have some content (text)")
    void checkEveryCommentContainsValidText() {

        assertTrue(commentaries.stream().map(CommentaryDTO::getComment).allMatch(StringUtils::isNotBlank));

    }

    @Test
    @DisplayName("every comment should be related to picture")
    void checkEveryCommentIsRelatedToPicture() {

        assertTrue(commentaries.stream().allMatch(c -> c.getPicture() != null));

    }

    @Test
    @DisplayName("every comment should be related to user")
    void checkEveryCommentIsRelatedToUser() {

        assertTrue(commentaries.stream().allMatch(c -> c.getUser() != null));

    }

}