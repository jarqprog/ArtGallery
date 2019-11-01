package com.jarqprog.artGallery.api.domains.artistic.commentary;


import com.jarqprog.artGallery.domain.artistic.CommentaryData;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentaryService {

    List<CommentaryData> getAllCommentaries();
    <T extends CommentaryData> List<T> getAllCommentaries(Class<T> clazz);

    List<CommentaryData> getAllCommentariesByPicture(long pictureId);
    <T extends CommentaryData> List<T> getAllCommentariesByPicture(long pictureId, Class<T> clazz);

    CommentaryData findCommentaryById(long id);
    <T extends CommentaryData> T findCommentaryById(long id, Class<T> clazz);

    long addCommentary(long pictureId, @NonNull CommentaryData commentaryData);

    void updateCommentary(long pictureId, long commentaryId, @NonNull CommentaryData commentaryData);

    void removeCommentary(long id);

    void validateCommentaryExists(long pictureId, long commentaryId);
}
