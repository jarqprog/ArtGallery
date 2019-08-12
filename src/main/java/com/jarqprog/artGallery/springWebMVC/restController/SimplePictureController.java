package com.jarqprog.artGallery.springWebMVC.restController;

import com.jarqprog.artGallery.domain.dto.PictureDTO;
import com.jarqprog.artGallery.domain.useCases.PictureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pictures")
public class SimplePictureController {

    @Autowired
    private PictureService pictureService;

    @GetMapping()
    public List<PictureDTO> getAllPictures() {
        return pictureService.getAllPictures();
    }

    @GetMapping("/{id}")
    public PictureDTO findPictureById(@PathVariable("id") long id) {
        return pictureService.findPictureById(id);
    }

    @PostMapping
    public PictureDTO addPicture(@RequestBody PictureDTO pictureDTO) {
        return pictureService.addPicture(pictureDTO);
    }

    @PutMapping("/{id}")
    public PictureDTO updatePicture(@PathVariable("id")long id, @RequestBody PictureDTO pictureDTO) {
        return pictureService.updatePicture(id, pictureDTO);
    }

    @DeleteMapping("/{id}")
    public void removePicture(@PathVariable("id") long id) {
        pictureService.removePicture(id);
    }
}
