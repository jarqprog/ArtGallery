package com.jarqprog.artapi.domains.commentary.controller;

import com.jarqprog.artapi.domains.commentary.CommentaryService;
import com.jarqprog.artapi.domains.commentary.dto.ApiCommentaryDTO;
import com.jarqprog.artapi.domains.commentary.dto.CommentaryFat;
import com.jarqprog.artapi.domains.commentary.dto.CommentaryThin;
import com.jarqprog.commonapi.constants.ApiConstants;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.jarqprog.commonapi.constants.OutputMode.FAT;


@RestController
@RequestMapping(ApiConstants.BASE_URL_PATH + "pictures/")
public class CommentaryController {

    @NonNull private final CommentaryService commentaryService;

    @Autowired
    public CommentaryController(@NonNull CommentaryService commentaryService) {
        this.commentaryService = commentaryService;
    }

    @GetMapping("commentaries")
    public List<? extends ApiCommentaryDTO> getAllCommentaries(@RequestParam(required = false, name = "mode") String mode) {
        return commentaryService.getAllCommentaries(getOutputClass(mode));
    }

    @GetMapping("{pictureId}/commentaries")
    public List<? extends ApiCommentaryDTO> getAllCommentariesByPicture(@PathVariable("pictureId") long pictureId,
                                                                        @RequestParam(required = false, name = "mode") String mode) {
        return commentaryService.getAllCommentariesByPicture(pictureId, getOutputClass(mode));
    }

    @GetMapping("{pictureId}/commentaries/{id}")
    public ApiCommentaryDTO findCommentaryById(@PathVariable("pictureId") long pictureId,
                                               @PathVariable("id") long id,
                                               @RequestParam(required = false, name = "mode") String mode) {
        commentaryService.validateCommentaryExists(pictureId, id);
        return commentaryService.findCommentaryById(id, getOutputClass(mode));
    }

    @PostMapping("{pictureId}/commentaries")
    public ResponseEntity addCommentary(@PathVariable("pictureId") long pictureId,
                                        @RequestBody ApiCommentaryDTO commentaryDTO) {

        long id = commentaryService.addCommentary(pictureId, commentaryDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{pictureId}/commentaries/{commentaryId}")
    public ResponseEntity updateCommentary(@PathVariable("pictureId") long pictureId,
                                           @PathVariable("commentaryId") long commentaryId,
                                           @RequestBody ApiCommentaryDTO commentaryDTO) {

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

    private Class<? extends ApiCommentaryDTO> getOutputClass(String mode) {
        Class<CommentaryThin> defaultOutput = CommentaryThin.class;

        if (mode == null) {
            return defaultOutput;
        }
        switch (mode) {
            case FAT:
                return CommentaryFat.class;
            default:
                return defaultOutput;
        }
    }
}
