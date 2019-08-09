package com.jarqprog.artGallery.repository;

import com.jarqprog.artGallery.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Set;

@Repository
@EnableTransactionManagement
public interface PictureRepository extends JpaRepository<Picture, Long> {

    Set<Picture> findAllPicturesByUserId(long userId);
    Set<Picture> findAllPicturesByAuthorId(long authorId);
}
