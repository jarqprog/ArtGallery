package com.jarqprog.artGallery.rest.controller.heavyDTOsREST;

import com.jarqprog.artGallery.domain.dto.heavyDto.CommentaryDTO;
import com.jarqprog.artGallery.domain.dto.lightDto.CommentaryDTOLight;
import com.jarqprog.artGallery.springData.useCases.CommentaryService;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/heavy/pictures/")
public class CommentaryController {

    private final CommentaryService commentaryService;

    @Autowired
    public CommentaryController(@NonNull CommentaryService commentaryService) {
        this.commentaryService = commentaryService;
    }

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
                                       @RequestBody CommentaryDTOLight commentaryDTO) {
        return commentaryService.addCommentary(pictureId, commentaryDTO);
    }

    @PutMapping("{pictureId}/commentaries/{commentaryId}")
    public CommentaryDTO updateCommentary(@PathVariable("pictureId") long pictureId,
                                          @PathVariable("commentaryId") long commentaryId,
                                          @RequestBody CommentaryDTOLight commentaryDTO) {
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
