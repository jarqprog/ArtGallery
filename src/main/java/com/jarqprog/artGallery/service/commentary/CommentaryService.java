package com.jarqprog.artGallery.service.commentary;


import com.jarqprog.artGallery.dto.CommentaryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentaryService {

    List<CommentaryDTO> getAllCommentaries();
    List<CommentaryDTO> getAllCommentariesByPicture(long pictureId);
    CommentaryDTO findCommentaryById(long pictureId, long commentaryId);
    CommentaryDTO findCommentaryById(long id);
    CommentaryDTO addCommentary(long pictureId, CommentaryDTO commentaryDTO);
    CommentaryDTO updateCommentary(long pictureId, long commentaryId, CommentaryDTO commentaryDTO);
    void removeCommentary(long pictureId, long commentaryId);
    void removeCommentary(long id);
}
