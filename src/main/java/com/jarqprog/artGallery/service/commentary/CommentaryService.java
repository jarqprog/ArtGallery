package com.jarqprog.artGallery.service.commentary;


import com.jarqprog.artGallery.dto.CommentaryDTO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public interface CommentaryService {

    List<CommentaryDTO> getAllCommentaries();
    List<CommentaryDTO> getAllCommentariesByPicture(long pictureId);
    CommentaryDTO findCommentaryById(long pictureId, long commentaryId) throws EntityNotFoundException;
    CommentaryDTO findCommentaryById(long id) throws EntityNotFoundException;
    CommentaryDTO addCommentary(long pictureId, CommentaryDTO commentaryDTO) ;
    CommentaryDTO updateCommentary(long pictureId, long commentaryId, CommentaryDTO commentaryDTO) throws EntityNotFoundException;
    boolean removeCommentary(long pictureId, long commentaryId) throws EntityNotFoundException;
    boolean removeCommentary(long id) throws EntityNotFoundException;
}
