package com.jarqprog.artGallery.api.rest.controller;

import com.jarqprog.artGallery.domain.dto.CommentaryDTO;
import com.jarqprog.artGallery.api.dataLogic.useCases.CommentaryService;
import com.jarqprog.artGallery.domain.dto.fatDTO.CommentaryFat;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.jarqprog.artGallery.api.rest.controller.OutputMode.FAT;


@RestController
@RequestMapping("/api/pictures/")
public class CommentaryController {

    @NonNull private final CommentaryService commentaryService;

    @Autowired
    public CommentaryController(@NonNull CommentaryService commentaryService) {
        this.commentaryService = commentaryService;
    }

    @GetMapping("commentaries")
    public List<? extends CommentaryDTO> getAllCommentaries(@RequestParam(required = false, name = "mode") String mode) {

        if (mode == null) {
            return commentaryService.getAllCommentaries();
        }

        switch (mode) {
            case FAT: return commentaryService.getAllCommentaries(CommentaryFat.class);
            default: return commentaryService.getAllCommentaries();
        }
    }

    @GetMapping("{pictureId}/commentaries")
    public List<? extends CommentaryDTO> getAllCommentariesByPicture(@PathVariable("pictureId") long pictureId,
                                            @RequestParam(required = false, name = "mode") String mode) {

        if (mode == null) {
            return commentaryService.getAllCommentariesByPicture(pictureId);
        }

        switch (mode) {
            case FAT: return commentaryService.getAllCommentariesByPicture(pictureId, CommentaryFat.class);
            default: return commentaryService.getAllCommentariesByPicture(pictureId);
        }
    }

    @GetMapping("{pictureId}/commentaries/{id}")
    public CommentaryDTO findCommentaryById(@PathVariable("pictureId") long pictureId,
                                            @PathVariable("id") long id,
                                            @RequestParam(required = false, name = "mode") String mode) {
        commentaryService.validateCommentaryExists(pictureId, id);

        if (mode == null) {
            return commentaryService.findCommentaryById(id);
        }
        switch (mode) {
            case FAT:
                return commentaryService.findCommentaryById(id, CommentaryFat.class);
            default:
                return commentaryService.findCommentaryById(id);
        }
    }

    @PostMapping("{pictureId}/commentaries")
    public ResponseEntity addCommentary(@PathVariable("pictureId") long pictureId,
                                                @RequestBody CommentaryDTO commentaryDTO) {
        long id = commentaryService.addCommentary(pictureId, commentaryDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{pictureId}/commentaries/{commentaryId}")
    public ResponseEntity updateCommentary(@PathVariable("pictureId") long pictureId,
                                           @PathVariable("commentaryId") long commentaryId,
                                           @RequestBody CommentaryDTO commentaryDTO) {
        commentaryService.updateCommentary(pictureId, commentaryId, commentaryDTO);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("{pictureId}/commentaries/{id}")
    public ResponseEntity removeCommentary(@PathVariable("pictureId") long pictureId,
                                 @PathVariable("id") long id) {
        commentaryService.validateCommentaryExists(pictureId, id);
        commentaryService.removeCommentary(id);
        return ResponseEntity.accepted().build();
    }
}
