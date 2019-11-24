package com.jarqprog.artapi.domains.picture.controller;


import com.jarqprog.artapi.domains.picture.PictureService;
import com.jarqprog.artapi.domains.picture.dto.ApiPictureDTO;
import com.jarqprog.artapi.domains.picture.dto.PictureFat;
import com.jarqprog.artapi.domains.picture.dto.PictureThin;
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
@RequestMapping(ApiConstants.BASE_URL_PATH + "pictures")
public class PictureController {

    @NonNull private final PictureService pictureService;

    @Autowired
    public PictureController(@NonNull PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping()
    public List<? extends ApiPictureDTO> getAllPictures(@RequestParam(required = false, name = "mode") String mode) {
        return pictureService.getAllPictures(getOutputClass(mode));
    }

    @GetMapping("/{id}")
    public ApiPictureDTO findPictureById(@PathVariable("id") long id,
                                         @RequestParam(required = false, name = "mode") String mode) {
        return pictureService.findPictureById(id, getOutputClass(mode));
    }

    @PostMapping
    public ResponseEntity addPicture(@RequestBody ApiPictureDTO pictureDTO) {
        long id = pictureService.addPicture(pictureDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePicture(@PathVariable("id")long id, @RequestBody ApiPictureDTO pictureDTO) {
        pictureService.updatePicture(id, pictureDTO);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removePicture(@PathVariable("id") long id) {
        pictureService.removePicture(id);
        return ResponseEntity.accepted().build();
    }

    private Class<? extends ApiPictureDTO> getOutputClass(String mode) {
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
