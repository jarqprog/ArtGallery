package com.jarqprog.artGallery.controller.rest;

import com.jarqprog.artGallery.dto.CommentaryDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface CommentaryController {

    List<CommentaryDTO> getAllCommentaries();

    List<CommentaryDTO> getAllCommentariesByPicture(@PathVariable("pictureId") long pictureId);

    CommentaryDTO findCommentaryById(@PathVariable("pictureId") long pictureId,
                                     @PathVariable("commentaryId") long commentaryId);

    CommentaryDTO findCommentaryById(@PathVariable("id") long id);

    CommentaryDTO addCommentary(@PathVariable("pictureId") long pictureId, @RequestBody CommentaryDTO commentaryDTO);

    CommentaryDTO updateCommentary(@PathVariable("pictureId") long pictureId,
                                   @PathVariable("commentaryId") long commentaryId,
                                   @RequestBody CommentaryDTO commentaryDTO);

    void removeCommentary(@PathVariable("id") long id);

    void removeCommentary(@PathVariable("pictureId") long pictureId, @PathVariable("commentaryId") long commentaryId);
}
