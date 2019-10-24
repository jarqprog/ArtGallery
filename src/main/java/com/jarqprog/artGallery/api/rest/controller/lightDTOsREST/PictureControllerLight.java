package com.jarqprog.artGallery.api.rest.controller.lightDTOsREST;

import com.jarqprog.artGallery.domain.dto.DtoConverter;
import com.jarqprog.artGallery.domain.dto.lightDto.PictureDTOLight;
import com.jarqprog.artGallery.api.dataLogic.useCases.PictureService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/light/pictures")
public class PictureControllerLight {

    @NonNull private final PictureService pictureService;
    @NonNull private final DtoConverter dtoConverter;

    @Autowired
    public PictureControllerLight(@NonNull PictureService pictureService,
                                  @NonNull DtoConverter dtoConverter) {
        this.pictureService = pictureService;
        this.dtoConverter = dtoConverter;
    }


    @GetMapping()
    public List<PictureDTOLight> getAllPictures() {
        return pictureService.getAllPictures()
                .stream()
                .map(h -> dtoConverter.convertHeavyToLight(h, PictureDTOLight.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PictureDTOLight findPictureById(@PathVariable("id") long id) {
        return dtoConverter.convertHeavyToLight(pictureService.findPictureById(id),
                PictureDTOLight.class);
    }

    @PostMapping
    public PictureDTOLight addPicture(@RequestBody PictureDTOLight pictureDTO) {
        return dtoConverter.convertHeavyToLight(pictureService.addPicture(pictureDTO),
                PictureDTOLight.class);
    }

    @PutMapping("/{id}")
    public PictureDTOLight updatePicture(@PathVariable("id")long id, @RequestBody PictureDTOLight pictureDTO) {
        return dtoConverter.convertHeavyToLight(pictureService.updatePicture(id, pictureDTO),
                PictureDTOLight.class);
    }

    @DeleteMapping("/{id}")
    public void removePicture(@PathVariable("id") long id) {
        pictureService.removePicture(id);
    }
}
