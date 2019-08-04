package com.jarqprog.artGallery.controller;

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
    public PictureDTO findById(@PathVariable("id") long id) {
        return pictureService.findById(id);
    }

    @Override
    @PostMapping
    public PictureDTO save(@RequestBody PictureDTO pictureDTO) {
        return pictureService.save(pictureDTO);
    }

    @Override
    @PutMapping("/{id}")
    public PictureDTO update(@PathVariable("id")long id, @RequestBody PictureDTO pictureDTO) {
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") long id) {
        pictureService.remove(id);
    }
}
