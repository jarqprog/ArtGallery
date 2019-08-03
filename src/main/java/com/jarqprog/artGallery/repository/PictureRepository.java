package com.jarqprog.artGallery.repository;

import com.jarqprog.artGallery.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

    Optional<Picture> findById(Long id);
    List<Picture> findByTitle(String title);
    List<Picture> findTop5ByTitle(String title);
    List<Picture> findByTitleIsNull();
    List<Picture> findAll();

}
