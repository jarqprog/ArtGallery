package com.jarqprog.artGallery.controller;

import com.jarqprog.artGallery.domain.Picture;
import com.jarqprog.artGallery.service.PictureService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class SimplePictureController implements PictureController  {

    private PictureService pictureService;

    @Override
    @GetMapping("/pictures")
    public List<Picture> getAllPictures() {
        return pictureService.getAllPictures();
    }

    @Override
    @PostMapping("/pictures")
    public <P extends Picture> P save(P picture) {
        return pictureService.save(picture);
    }
}
