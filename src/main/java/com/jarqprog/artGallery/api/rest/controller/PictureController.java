package com.jarqprog.artGallery.api.rest.controller;

import com.jarqprog.artGallery.domain.dto.PictureDTO;
import com.jarqprog.artGallery.api.dataLogic.useCases.PictureService;
import com.jarqprog.artGallery.domain.dto.fatDTO.PictureFat;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.jarqprog.artGallery.api.rest.controller.OutputMode.FAT;

@RestController
@RequestMapping("/api/pictures")
public class PictureController {

    @NonNull private final PictureService pictureService;

    @Autowired
    public PictureController(@NonNull PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping()
    public List<? extends PictureDTO> getAllPictures(@RequestParam(required = false, name = "mode") String mode) {
        if (mode == null) {
            return pictureService.getAllPictures();
        }

        switch (mode) {
            case FAT: return pictureService.getAllPictures(PictureFat.class);
            default: return pictureService.getAllPictures();
        }
    }

    @GetMapping("/{id}")
    public PictureDTO findPictureById(@PathVariable("id") long id,
                                      @RequestParam(required = false, name = "mode") String mode) {
        if (mode == null) {
            return pictureService.findPictureById(id);
        }

        switch (mode) {
            case FAT: return pictureService.findPictureById(id, PictureFat.class);
            default: return pictureService.findPictureById(id);
        }
    }

    @PostMapping
    public ResponseEntity addPicture(@RequestBody PictureDTO pictureDTO) {

        long id = pictureService.addPicture(pictureDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePicture(@PathVariable("id")long id, @RequestBody PictureDTO pictureDTO) {
        pictureService.updatePicture(id, pictureDTO);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removePicture(@PathVariable("id") long id) {
        pictureService.removePicture(id);
        return ResponseEntity.accepted().build();
    }
}
