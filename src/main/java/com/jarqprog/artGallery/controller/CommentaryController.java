package com.jarqprog.artGallery.controller;

import com.jarqprog.artGallery.dto.CommentaryDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface CommentaryController {

    List<CommentaryDTO> getAllCommentaries();
    List<CommentaryDTO> getAllCommentariesByPicture(@PathVariable("pictureId") long pictureId);
    CommentaryDTO findById(@PathVariable("id") long id);
    CommentaryDTO addCommentary(@PathVariable("pictureId") long pictureId, @RequestBody CommentaryDTO commentaryDTO);
    CommentaryDTO updateContact(@PathVariable("pictureId") long pictureId,
                                @PathVariable("commentaryId") long commentaryId,
                                @RequestBody CommentaryDTO commentaryDTO);
    void remove(@PathVariable("id") long id);
}
