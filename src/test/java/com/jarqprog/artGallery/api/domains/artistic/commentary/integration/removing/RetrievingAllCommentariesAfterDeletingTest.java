package com.jarqprog.artGallery.api.domains.artistic.commentary.integration.removing;

import com.jarqprog.artGallery.api.ApiConstants;
import com.jarqprog.artGallery.api.SpringServiceTestConfig;
import com.jarqprog.artGallery.api.domains.artistic.commentary.CommentaryService;
import com.jarqprog.artGallery.api.domains.artistic.commentary.CommentaryRepository;
import com.jarqprog.artGallery.api.domains.artistic.commentary.dto.CommentaryDTO;
import lombok.NonNull;
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
class RetrievingAllCommentariesAfterDeletingTest {

    private final CommentaryService commentaryService;
    private final CommentaryRepository commentaryRepository;

    @Autowired
    public RetrievingAllCommentariesAfterDeletingTest(@NonNull CommentaryService commentaryService,
                                                      @NonNull CommentaryRepository commentaryRepository) {
        this.commentaryService = commentaryService;
        this.commentaryRepository = commentaryRepository;
    }

    @Test
    @DisplayName("should retrieve empty list")
    void checkCommentariesAfterRemovingAllOfThem() {

        int expected = 0;

        commentaryRepository.deleteAll();
        List<CommentaryDTO> commentaries = commentaryService.getAllCommentaries();

        assertEquals(expected, commentaries.size());
    }

    @Test
    @DisplayName("should retrieve 3 commentaries")
    void checkCommentariesAfterRemovingOneOfThem() {

        int expected = 3;
        long toRemoveId = 1;

        commentaryService.removeCommentary(toRemoveId);
        List<CommentaryDTO> commentaries = commentaryService.getAllCommentaries();

        assertEquals(expected, commentaries.size());
        assertTrue(commentaries.stream().allMatch(c -> c.getId() != toRemoveId));
    }
}
