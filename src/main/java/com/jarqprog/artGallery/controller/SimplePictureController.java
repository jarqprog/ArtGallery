package com.jarqprog.artGallery.controller;

import com.jarqprog.artGallery.dto.PictureDTO;
import com.jarqprog.artGallery.service.picture.PictureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SimplePictureController implements PictureController  {

    @Autowired
    private PictureService pictureService;

    @Override
    @GetMapping("/pictures")
    public List<PictureDTO> getAllPictures() {
        return pictureService.getAllPictures();
    }

    @Override
    @GetMapping("/pictures/{id}")
    public PictureDTO findById(@PathVariable("id") long id) {
        return pictureService.findById(id);
    }

    @Override
    @PostMapping("/pictures")
    public PictureDTO save(@RequestBody PictureDTO pictureDTO) {
        return pictureService.save(pictureDTO);
    }

    @Override
    @DeleteMapping("/pictures/{id}")
    public void remove(@PathVariable("id") long id) {
        pictureService.remove(id);
    }
}
