package com.jarqprog.artGallery.api.domains.artistic.commentary;


import com.jarqprog.artGallery.domain.artistic.Commentary;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentaryService {

    List<Commentary> getAllCommentaries();
    <T extends Commentary> List<T> getAllCommentaries(Class<T> clazz);

    List<Commentary> getAllCommentariesByPicture(long pictureId);
    <T extends Commentary> List<T> getAllCommentariesByPicture(long pictureId, Class<T> clazz);

    Commentary findCommentaryById(long id);
    <T extends Commentary> T findCommentaryById(long id, Class<T> clazz);

    long addCommentary(long pictureId, @NonNull Commentary commentary);

    void updateCommentary(long pictureId, long commentaryId, @NonNull Commentary commentary);

    void removeCommentary(long id);

    void validateCommentaryExists(long pictureId, long commentaryId);
}
