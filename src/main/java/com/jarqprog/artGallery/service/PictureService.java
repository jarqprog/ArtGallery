package com.jarqprog.artGallery.service;

import com.jarqprog.artGallery.domain.Picture;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PictureService {

    List<Picture> getAllPictures();
    <P extends Picture> P save(P picture);
    Optional<Picture> findById(Long id);
    void remove(Long id);

}
