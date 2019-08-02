package com.jarqprog.artGallery.repository;

import com.jarqprog.artGallery.domain.Picture;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PictureRepository extends CrudRepository<Picture, Long> {

    Optional<Picture> findById(Long id);
    <P extends Picture> P save(P picture);
    List<Picture> findByTitle(String title);
    List<Picture> findTop5ByTitle(String title);
    List<Picture> findByTitleIsNull(String title);
    List<Picture> findAll();

}
