package com.jarqprog.artGallery.rest.restController;

import com.jarqprog.artGallery.domain.dto.CommentaryDTO;
import com.jarqprog.artGallery.domain.useCases.CommentaryService;
import com.jarqprog.artGallery.domain.useCases.PictureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pictures/")
public class SimpleCommentaryController {

    @Autowired private PictureService pictureService;
    @Autowired private CommentaryService commentaryService;



    @GetMapping("commentaries")
    public List<CommentaryDTO> getAllCommentaries() {
        return commentaryService.getAllCommentaries();
    }

    @GetMapping("{pictureId}/commentaries")
    public List<CommentaryDTO> getAllCommentariesByPicture(@PathVariable("pictureId") long pictureId) {
        return commentaryService.getAllCommentariesByPicture(pictureId);

    }

    @GetMapping("{pictureId}/commentaries/{commentaryId}")
    public CommentaryDTO findCommentaryById(@PathVariable("pictureId") long pictureId,
                                            @PathVariable("commentaryId") long commentaryId) {
        return commentaryService.findCommentaryById(pictureId, commentaryId);

    }

    @GetMapping("commentaries/{id}")
    public CommentaryDTO findCommentaryById(@PathVariable("id") long id) {
        return commentaryService.findCommentaryById(id);

    }

    @PostMapping("{pictureId}/commentaries")
    public CommentaryDTO addCommentary(@PathVariable("pictureId") long pictureId,
                                       @RequestBody CommentaryDTO commentaryDTO) {
        return commentaryService.addCommentary(pictureId, commentaryDTO);

    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("{pictureId}/commentaries/secured/{commentaryId}")
    public CommentaryDTO updateCommentary(@PathVariable("pictureId") long pictureId,
                                          @PathVariable("commentaryId") long commentaryId,
                                          @RequestBody CommentaryDTO commentaryDTO) {
        return commentaryService.updateCommentary(pictureId, commentaryId, commentaryDTO);

    }

    @DeleteMapping("commentaries/{id}")
    public void removeCommentary(@PathVariable("id") long id) {
        commentaryService.removeCommentary(id);

    }

    @DeleteMapping("{pictureId}/commentaries/{commentaryId}")
    public void removeCommentary(@PathVariable("pictureId") long pictureId,
                                 @PathVariable("commentaryId") long commentaryId) {
        commentaryService.removeCommentary(pictureId, commentaryId);
    }
}
