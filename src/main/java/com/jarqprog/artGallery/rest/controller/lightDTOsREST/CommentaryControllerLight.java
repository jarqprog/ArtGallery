package com.jarqprog.artGallery.rest.controller.lightDTOsREST;

import com.jarqprog.artGallery.domain.dto.DtoConverter;
import com.jarqprog.artGallery.domain.dto.lightDto.CommentaryDTOLight;
import com.jarqprog.artGallery.springData.useCases.CommentaryService;
import com.jarqprog.artGallery.springData.useCases.PictureService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/light/pictures/")
public class CommentaryControllerLight {

    @NonNull private final PictureService pictureService;
    @NonNull private final CommentaryService commentaryService;
    @NonNull private final DtoConverter dtoConverter;

    @Autowired
    public CommentaryControllerLight(@NonNull PictureService pictureService,
                                     @NonNull CommentaryService commentaryService,
                                     @NonNull DtoConverter dtoConverter) {
        this.pictureService = pictureService;
        this.commentaryService = commentaryService;
        this.dtoConverter = dtoConverter;
    }


    @GetMapping("commentaries")
    public List<CommentaryDTOLight> getAllCommentaries() {
        return commentaryService.getAllCommentaries()
                .stream()
                .map(h -> dtoConverter.convertHeavyToLight(h, CommentaryDTOLight.class))
                .collect(Collectors.toList());
    }

    @GetMapping("{pictureId}/commentaries")
    public List<CommentaryDTOLight> getAllCommentariesByPicture(@PathVariable("pictureId") long pictureId) {
        return commentaryService.getAllCommentariesByPicture(pictureId)
                .stream()
                .map(h -> dtoConverter.convertHeavyToLight(h, CommentaryDTOLight.class))
                .collect(Collectors.toList());
    }

    @GetMapping("{pictureId}/commentaries/{commentaryId}")
    public CommentaryDTOLight findCommentaryById(@PathVariable("pictureId") long pictureId,
                                            @PathVariable("commentaryId") long commentaryId) {
        return dtoConverter
                .convertHeavyToLight(commentaryService.findCommentaryById(pictureId, commentaryId),
                        CommentaryDTOLight.class);
    }

    @GetMapping("commentaries/{id}")
    public CommentaryDTOLight findCommentaryById(@PathVariable("id") long id) {
        return dtoConverter
                .convertHeavyToLight(commentaryService.findCommentaryById(id), CommentaryDTOLight.class);
    }

    @PostMapping("{pictureId}/commentaries")
    public CommentaryDTOLight addCommentary(@PathVariable("pictureId") long pictureId,
                                       @RequestBody CommentaryDTOLight commentaryDTO) {
        return dtoConverter
                .convertHeavyToLight(commentaryService.addCommentary(pictureId, commentaryDTO),
                        CommentaryDTOLight.class);
    }

    @PutMapping("{pictureId}/commentaries/{commentaryId}")
    public CommentaryDTOLight updateCommentary(@PathVariable("pictureId") long pictureId,
                                          @PathVariable("commentaryId") long commentaryId,
                                          @RequestBody CommentaryDTOLight commentaryDTO) {
        return dtoConverter
                .convertHeavyToLight(commentaryService.updateCommentary(pictureId, commentaryId, commentaryDTO),
                        CommentaryDTOLight.class);
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
