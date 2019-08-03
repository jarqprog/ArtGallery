package com.jarqprog.artGallery.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.jarqprog.artGallery.domain.Picture;
import com.jarqprog.artGallery.service.picture.PictureService;

import com.jarqprog.artGallery.view.jsonView.View;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class SimplePictureController implements PictureController  {

    private final PictureService pictureService;

    @Autowired
    public SimplePictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @Override
    @GetMapping("/pictures")
    @JsonView(View.JsonPicture.class)
    public List<Picture> getAllPictures() {
        return pictureService.getAllPictures();
    }

    @Override
    @GetMapping("/pictures/{id}")
    public Picture findById(@PathVariable("id") Long id) {
        return pictureService.findById(id);
    }

    @Override
    @PostMapping("/pictures")
    @JsonView(View.JsonPicture.class)
    public Picture save(@RequestBody Picture picture) {
        return pictureService.save(picture);
    }

    @Override
    @DeleteMapping("/pictures/{id}")
    public void remove(@PathVariable("id") Long id) {
        pictureService.remove(id);
    }
}
