package com.jarqprog.artGallery.api.rest.controller;

import com.jarqprog.artGallery.domain.components.DtoConverter;
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
    @NonNull private final DtoConverter dtoConverter;

    @Autowired
    public CommentaryController(@NonNull CommentaryService commentaryService,
                                @NonNull DtoConverter dtoConverter) {
        this.commentaryService = commentaryService;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping("commentaries")
    public List<? extends CommentaryDTO> getAllCommentaries(@RequestParam(required = false, name = "mode") String mode) {

        switch (mode) {
            case FAT: return commentaryService.getAllCommentaries(CommentaryFat.class);
            default: return commentaryService.getAllCommentaries();
        }
    }

    @GetMapping("{pictureId}/commentaries")
    public List<? extends CommentaryDTO> getAllCommentariesByPicture(@PathVariable("pictureId") long pictureId,
                                            @RequestParam(required = false, name = "mode") String mode) {

        switch (mode) {
            case FAT: return commentaryService.getAllCommentariesByPicture(pictureId, CommentaryFat.class);
            default: return commentaryService.getAllCommentariesByPicture(pictureId);
        }
    }

    @GetMapping("commentaries/{id}")
    public CommentaryDTO findCommentaryById(@PathVariable("id") long id,
                                            @RequestParam(required = false, name = "mode") String mode) {
        switch (mode) {
            case FAT: return commentaryService.findCommentaryById(id, CommentaryFat.class);
            default: return commentaryService.findCommentaryById(id);
        }
    }

    @PostMapping("{pictureId}/commentaries")
    public ResponseEntity addCommentary(@PathVariable("pictureId") long pictureId,
                                                @RequestBody CommentaryDTO commentaryDTO) {
        CommentaryDTO created = commentaryService.addCommentary(pictureId, commentaryDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{pictureId}/commentaries/{commentaryId}")
    public ResponseEntity updateCommentary(@PathVariable("pictureId") long pictureId,
                                           @PathVariable("commentaryId") long commentaryId,
                                           @RequestBody CommentaryDTO commentaryDTO) {
        CommentaryDTO created = commentaryService.updateCommentary(pictureId, commentaryId, commentaryDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("commentaries/{id}")
    public void removeCommentary(@PathVariable("id") long id) {
        commentaryService.removeCommentary(id);
    }

}
