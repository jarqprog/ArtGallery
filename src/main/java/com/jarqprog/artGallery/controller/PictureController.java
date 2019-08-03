package com.jarqprog.artGallery.controller;

import com.jarqprog.artGallery.domain.Picture;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface PictureController {

    List<Picture> getAllPictures();
    <P extends Picture> P save(@RequestBody P picture);
    void remove(@PathVariable("id") Long id);
}
