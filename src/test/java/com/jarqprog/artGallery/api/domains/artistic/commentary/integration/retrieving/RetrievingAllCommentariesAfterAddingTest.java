package com.jarqprog.artGallery.api.domains.artistic.commentary.integration.retrieving;

import com.jarqprog.artGallery.api.ApiConstants;
import com.jarqprog.artGallery.api.SpringServiceTestConfig;
import com.jarqprog.artGallery.api.domains.artistic.commentary.CommentaryService;
import com.jarqprog.artGallery.api.domains.artistic.commentary.dto.CommentaryDTO;
import com.jarqprog.artGallery.api.domains.artistic.commentary.dto.CommentaryThin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
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
@EnabledIfSystemProperty(named = "spring.profiles.active", matches = ApiConstants.TEST_PROFILE)
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

        final String comment = "a";
        final long pictureId = 1;
        final String login = "login";

        CommentaryThin commentary = CommentaryThin.createWith()
                .comment(comment)
                .userLogin(login)
                .pictureId(pictureId)
                .build();

        commentaryService.addCommentary(pictureId, commentary);

        int expectedSize = 5;

        List<CommentaryDTO> commentaries = commentaryService.getAllCommentaries();

        assertEquals(expectedSize, commentaries.size());
        assertTrue(commentaries.stream().anyMatch(c -> c.getComment().equals(comment)));
    }
}
