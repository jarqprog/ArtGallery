package com.jarqprog.artGallery.springData.services.commentaries;

import com.jarqprog.artGallery.SpringServiceTestConfig;
import com.jarqprog.artGallery.domain.useCases.CommentaryService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringServiceTestConfig.class)
class RetrievingAllCommentariesTest {

    private static final Logger logger = Logger.getLogger(RetrievingAllCommentariesTest.class);

    @Autowired
    private CommentaryService commentaryService;

    @Test
    void findCommentaryById() {
        Object object = commentaryService.findCommentaryById(1L);
        logger.info(String.valueOf(object));
    }

    @Test
    void findCommentaryById1() {
        commentaryService.findCommentaryById(1L);
    }

}