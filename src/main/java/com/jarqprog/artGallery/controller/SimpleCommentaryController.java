package com.jarqprog.artGallery.controller;

import com.jarqprog.artGallery.dto.CommentaryDTO;
import com.jarqprog.artGallery.service.commentary.CommentaryService;
import com.jarqprog.artGallery.service.picture.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pictures/")
public class SimpleCommentaryController implements CommentaryController {

    @Autowired private PictureService pictureService;
    @Autowired private CommentaryService commentaryService;

    @Override
    @GetMapping("commentaries")
    public List<CommentaryDTO> getAllCommentaries() {
        return commentaryService.getAllCommentaries();
    }

    @Override
    @GetMapping("{pictureId}/commentaries")
    public List<CommentaryDTO> getAllCommentariesByPicture(@PathVariable("pictureId") long pictureId) {
        return commentaryService.getAllCommentariesByPicture(pictureId);
    }

    @Override
    @GetMapping("{pictureId}/commentaries/{commentaryId}")
    public CommentaryDTO findCommentaryById(@PathVariable("pictureId") long pictureId,
                                            @PathVariable("commentaryId") long commentaryId) {
        return commentaryService.findCommentaryById(pictureId, commentaryId);
    }

    @Override
    @GetMapping("commentaries/{id}")
    public CommentaryDTO findCommentaryById(@PathVariable("id") long id) {
        return commentaryService.findCommentaryById(id);
    }

    @Override
    @PostMapping("{pictureId}/commentaries")
    public CommentaryDTO addCommentary(@PathVariable("pictureId") long pictureId,
                                       @RequestBody CommentaryDTO commentaryDTO) {
        return commentaryService.addCommentary(pictureId, commentaryDTO);
    }

    @Override
    @PutMapping("{pictureId}/commentaries/{commentaryId}")
    public CommentaryDTO updateCommentary(@PathVariable("pictureId") long pictureId,
                                          @PathVariable("commentaryId") long commentaryId,
                                          @RequestBody CommentaryDTO commentaryDTO) {
        return commentaryService.updateCommentary(pictureId, commentaryId, commentaryDTO);
    }

    @Override
    @DeleteMapping("commentaries/{id}")
    public void removeCommentary(@PathVariable("id") long id) {
        commentaryService.removeCommentary(id);
    }

    @Override
    @DeleteMapping("{pictureId}/commentaries/{commentaryId}")
    public void removeCommentary(@PathVariable("pictureId") long pictureId,
                                 @PathVariable("commentaryId") long commentaryId) {
        commentaryService.removeCommentary(pictureId, commentaryId);
    }
}
