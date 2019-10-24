package com.jarqprog.artGallery.api.dataLogic.useCases;


import com.jarqprog.artGallery.domain.dto.heavyDto.CommentaryDTO;
import com.jarqprog.artGallery.domain.dto.lightDto.CommentaryDTOLight;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentaryService {

    List<CommentaryDTO> getAllCommentaries();
    List<CommentaryDTO> getAllCommentariesByPicture(long pictureId);
    CommentaryDTO findCommentaryById(long pictureId, long commentaryId);
    CommentaryDTO findCommentaryById(long id);
    CommentaryDTO addCommentary(long pictureId, @NonNull CommentaryDTOLight commentaryDTO);
    CommentaryDTO updateCommentary(long pictureId, long commentaryId, @NonNull CommentaryDTOLight commentaryDTO);
    void removeCommentary(long pictureId, long commentaryId);
    void removeCommentary(long id);
}
