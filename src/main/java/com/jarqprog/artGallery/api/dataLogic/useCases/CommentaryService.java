package com.jarqprog.artGallery.api.dataLogic.useCases;


import com.jarqprog.artGallery.domain.dto.CommentaryDTO;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentaryService {

    List<CommentaryDTO> getAllCommentaries();
    <T extends CommentaryDTO> List<T> getAllCommentaries(Class<T> clazz);

    List<CommentaryDTO> getAllCommentariesByPicture(long pictureId);
    <T extends CommentaryDTO> List<T> getAllCommentariesByPicture(long pictureId, Class<T> clazz);

    CommentaryDTO findCommentaryById(long id);
    <T extends CommentaryDTO> T findCommentaryById(long id, Class<T> clazz);

    long addCommentary(long pictureId, @NonNull CommentaryDTO commentaryDTO);

    void updateCommentary(long pictureId, long commentaryId, @NonNull CommentaryDTO commentaryDTO);

    void removeCommentary(long id);

    void validateCommentaryExists(long pictureId, long commentaryId);
}
