package com.jarqprog.artGallery.controller;

import com.jarqprog.artGallery.domain.Picture;
import com.jarqprog.artGallery.service.PictureService;
import lombok.Lombok;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class SimplePictureController implements PictureController  {

    private final PictureService pictureService;

    @Autowired
    public SimplePictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @Override
    @GetMapping("/pictures")
    public List<Picture> getAllPictures() {
        return pictureService.getAllPictures();
    }

    @Override
    @PostMapping("/pictures")
    public <P extends Picture> P save(@RequestBody P picture) {
        return pictureService.save(picture);
    }
}
