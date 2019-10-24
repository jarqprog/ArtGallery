package com.jarqprog.artGallery.rest.controller.heavyDTOsREST;

import com.jarqprog.artGallery.domain.dto.heavyDto.PictureDTO;
import com.jarqprog.artGallery.domain.dto.lightDto.PictureDTOLight;
import com.jarqprog.artGallery.springData.useCases.PictureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/heavy/pictures")
public class PictureController {

    private final PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping()
    public List<PictureDTO> getAllPictures() {
        return pictureService.getAllPictures();
    }

    @GetMapping("/{id}")
    public PictureDTO findPictureById(@PathVariable("id") long id) {
        return pictureService.findPictureById(id);
    }

    @PostMapping
    public PictureDTO addPicture(@RequestBody PictureDTOLight pictureDTO) {
        return pictureService.addPicture(pictureDTO);
    }

    @PutMapping("/{id}")
    public PictureDTO updatePicture(@PathVariable("id")long id, @RequestBody PictureDTOLight pictureDTO) {
        return pictureService.updatePicture(id, pictureDTO);
    }

    @DeleteMapping("/{id}")
    public void removePicture(@PathVariable("id") long id) {
        pictureService.removePicture(id);
    }
}
