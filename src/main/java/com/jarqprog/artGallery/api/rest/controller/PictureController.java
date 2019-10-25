package com.jarqprog.artGallery.api.rest.controller;

import com.jarqprog.artGallery.domain.components.DtoConverter;
import com.jarqprog.artGallery.domain.dto.PictureDTO;
import com.jarqprog.artGallery.api.dataLogic.useCases.PictureService;
import com.jarqprog.artGallery.domain.dto.fatDTO.PictureFat;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.jarqprog.artGallery.api.rest.controller.OutputMode.FAT;

@RestController
@RequestMapping("/api/pictures")
public class PictureController {

    @NonNull private final PictureService pictureService;
    @NonNull private final DtoConverter dtoConverter;

    @Autowired
    public PictureController(@NonNull PictureService pictureService,
                             @NonNull DtoConverter dtoConverter) {
        this.pictureService = pictureService;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping()
    public List<? extends PictureDTO> getAllPictures(@RequestParam(required = false, name = "mode") String mode) {
        switch (mode) {
            case FAT: return pictureService.getAllPictures(PictureFat.class);
            default: return pictureService.getAllPictures();
        }
    }

    @GetMapping("/{id}")
    public PictureDTO findPictureById(@PathVariable("id") long id,
                                      @RequestParam(required = false, name = "mode") String mode) {
        switch (mode) {
            case FAT: return pictureService.findPictureById(id, PictureFat.class);
            default: return pictureService.findPictureById(id);
        }
    }

    @PostMapping
    public PictureDTO addPicture(@RequestBody PictureDTO pictureDTO) {
        return pictureService.addPicture(pictureDTO);
    }

    @PutMapping("/{id}")
    public PictureDTO updatePicture(@PathVariable("id")long id, @RequestBody PictureDTO pictureDTO) {
        return pictureService.updatePicture(id, pictureDTO);
    }

    @DeleteMapping("/{id}")
    public void removePicture(@PathVariable("id") long id) {
        pictureService.removePicture(id);
    }
}
