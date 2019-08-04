package com.jarqprog.artGallery.controller;

import com.jarqprog.artGallery.dto.PictureDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface PictureController {

    List<PictureDTO> getAllPictures();
    PictureDTO findById(Long id);
    PictureDTO save(@RequestBody PictureDTO picture);
    void remove(@PathVariable("id") Long id);
}
