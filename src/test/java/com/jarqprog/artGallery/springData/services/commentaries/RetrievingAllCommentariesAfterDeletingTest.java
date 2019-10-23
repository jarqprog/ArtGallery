package com.jarqprog.artGallery.springData.services.commentaries;

import com.jarqprog.artGallery.SpringServiceTestConfig;
import com.jarqprog.artGallery.domain.dto.heavyDto.CommentaryDTO;
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

import static org.junit.jupiter.api.Assertions.*;

/**
 * Values used in test methods are related to values set in InitialDataLoader
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringServiceTestConfig.class)
@Rollback
@Transactional
class RetrievingAllCommentariesAfterDeletingTest {

    private final CommentaryService commentaryService;
    private final CommentaryRepository commentaryRepository;

    @Autowired
    public RetrievingAllCommentariesAfterDeletingTest(CommentaryService commentaryService,
                                                      CommentaryRepository commentaryRepository) {
        assert commentaryService != null;
        assert commentaryRepository != null;
        this.commentaryService = commentaryService;
        this.commentaryRepository = commentaryRepository;
    }

//    @Test
//    @DisplayName("should retrieve empty list")
//    void checkCommentariesAfterRemovingAllOfThem() {
//
//        int expected = 0;
//
//        commentaryRepository.deleteAll();
//        List<CommentaryDTO> commentaries = commentaryService.getAllCommentaries();
//
//        assertEquals(expected, commentaries.size());
//    }
//
//    @Test
//    @DisplayName("should retrieve 3 commentaries")
//    void checkCommentariesAfterRemovingOneOfThem() {
//
//        int expected = 3;
//        long toRemoveId = 1;
//
//        commentaryService.removeCommentary(toRemoveId);
//        List<CommentaryDTO> commentaries = commentaryService.getAllCommentaries();
//
//        assertEquals(expected, commentaries.size());
//        assertTrue(commentaries.stream().allMatch(c -> c.getId() != toRemoveId));
//    }
}
