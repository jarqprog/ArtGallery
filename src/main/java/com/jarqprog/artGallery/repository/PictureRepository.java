package com.jarqprog.artGallery.repository;

import com.jarqprog.artGallery.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import java.util.List;

@Repository
@EnableTransactionManagement
public interface PictureRepository extends JpaRepository<Picture, Long> {

    List<Picture> findByTitle(String title);
    List<Picture> findTop5ByTitle(String title);
    List<Picture> findByTitleIsNull();

}
