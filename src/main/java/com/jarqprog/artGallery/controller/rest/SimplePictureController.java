package com.jarqprog.artGallery.controller.rest;

import com.jarqprog.artGallery.dto.PictureDTO;
import com.jarqprog.artGallery.service.picture.PictureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pictures")
public class SimplePictureController implements PictureController  {

    @Autowired
    private PictureService pictureService;

    @Override
    @GetMapping()
    public List<PictureDTO> getAllPictures() {
        return pictureService.getAllPictures();
    }

    @Override
    @GetMapping("/{id}")
    public PictureDTO findPictureById(@PathVariable("id") long id) {
        return pictureService.findPictureById(id);
    }

    @Override
    @PostMapping
    public PictureDTO addPicture(@RequestBody PictureDTO pictureDTO) {
        return pictureService.addPicture(pictureDTO);
    }

    @Override
    @PutMapping("/{id}")
    public PictureDTO updatePicture(@PathVariable("id")long id, @RequestBody PictureDTO pictureDTO) {
        return pictureService.updatePicture(id, pictureDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public void removePicture(@PathVariable("id") long id) {
        pictureService.removePicture(id);
    }
}
