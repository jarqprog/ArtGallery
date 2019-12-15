package com.jarqprog.artapi.domains.commentary;


import com.jarqprog.artapi.domains.commentary.dto.ApiCommentaryDTO;
import com.jarqprog.artdomain.commentary.CommentaryData;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentaryService {

    List<ApiCommentaryDTO> getAllCommentaries();
    <T extends ApiCommentaryDTO> List<T> getAllCommentaries(Class<T> clazz);

    List<ApiCommentaryDTO> getAllCommentariesByPicture(long pictureId);
    <T extends ApiCommentaryDTO> List<T> getAllCommentariesByPicture(long pictureId, Class<T> clazz);

    ApiCommentaryDTO findCommentaryById(long id);
    <T extends ApiCommentaryDTO> T findCommentaryById(long id, Class<T> clazz);

    long addCommentary(long pictureId, @NonNull CommentaryData commentaryData);

    void updateCommentary(long pictureId, long commentaryId, @NonNull CommentaryData commentaryData);

    void removeCommentary(long id);

    void validateCommentaryExists(long pictureId, long commentaryId);
}
