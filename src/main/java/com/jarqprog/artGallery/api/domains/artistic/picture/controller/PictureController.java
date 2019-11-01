package com.jarqprog.artGallery.api.domains.artistic.picture.controller;


import com.jarqprog.artGallery.api.ApiConstants;
import com.jarqprog.artGallery.api.domains.artistic.picture.PictureService;
import com.jarqprog.artGallery.api.domains.artistic.picture.model.PictureDTO;
import com.jarqprog.artGallery.api.domains.artistic.picture.model.PictureThin;
import com.jarqprog.artGallery.api.domains.artistic.picture.model.PictureFat;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.jarqprog.artGallery.api.domains.OutputMode.FAT;

@RestController
@RequestMapping(ApiConstants.BASE_URL_PATH + "pictures")
public class PictureController {

    @NonNull private final PictureService pictureService;

    @Autowired
    public PictureController(@NonNull PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping()
    public List<? extends PictureDTO> getAllPictures(@RequestParam(required = false, name = "mode") String mode) {
        return pictureService.getAllPictures(getOutputClass(mode));
    }

    @GetMapping("/{id}")
    public PictureDTO findPictureById(@PathVariable("id") long id,
                                       @RequestParam(required = false, name = "mode") String mode) {
        return pictureService.findPictureById(id, getOutputClass(mode));
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

    private Class<? extends PictureDTO> getOutputClass(String mode) {
        Class<PictureThin> defaultOutput = PictureThin.class;
        if (mode == null) {
            return defaultOutput;
        }
        switch (mode) {
            case FAT:
                return PictureFat.class;
            default:
                return defaultOutput;
        }
    }
}
