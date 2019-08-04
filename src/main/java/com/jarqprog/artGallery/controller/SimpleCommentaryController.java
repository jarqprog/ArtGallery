package com.jarqprog.artGallery.controller;

import com.jarqprog.artGallery.dto.CommentaryDTO;
import com.jarqprog.artGallery.service.commentary.CommentaryService;
import com.jarqprog.artGallery.service.picture.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class SimpleCommentaryController implements CommentaryController {

    @Autowired private PictureService pictureService;
    @Autowired private CommentaryService commentaryService;

    @Override
    public List<CommentaryDTO> getAllCommentaries() {
        return null;
    }

    @Override
    public List<CommentaryDTO> getAllCommentariesByPicture(long pictureId) {
        return null;
    }

    @Override
    public CommentaryDTO findById(long id) {
        return null;
    }

    @Override
    public CommentaryDTO addCommentary(long pictureId, CommentaryDTO commentaryDTO) {
        return null;
    }

    @Override
    public CommentaryDTO updateContact(long pictureId, long commentaryId, CommentaryDTO commentaryDTO) {
        return null;
    }

    @Override
    public void remove(long id) {

    }
}
