package com.jarqprog.artGallery.repository;

import com.jarqprog.artGallery.domain.Picture;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface  PictureRepository extends Repository<Picture, Long> {

    Optional<Picture> findById(Long id);

    Picture save(Picture picture);

    List<Picture> findByTitle(String title);

    List<Picture> findAll();

}
