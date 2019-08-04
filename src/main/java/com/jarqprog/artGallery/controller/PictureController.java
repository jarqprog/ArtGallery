package com.jarqprog.artGallery.controller;

import com.jarqprog.artGallery.dto.PictureDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface PictureController {

    List<PictureDTO> getAllPictures();
    PictureDTO findPictureById(@PathVariable("id") long id);
    PictureDTO addPicture(@RequestBody PictureDTO pictureDTO);
    PictureDTO updatePicture(@PathVariable("id") long id, @RequestBody PictureDTO pictureDTO);
    void removePicture(@PathVariable("id") long id);
}
