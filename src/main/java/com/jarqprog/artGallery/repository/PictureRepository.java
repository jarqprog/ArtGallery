package com.jarqprog.artGallery.repository;

import com.jarqprog.artGallery.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;
import java.util.Optional;

@Repository
@EnableTransactionManagement
public interface PictureRepository extends JpaRepository<Picture, Long> {

    Optional<Picture> findById(Long id);
    List<Picture> findByTitle(String title);
    List<Picture> findTop5ByTitle(String title);
    List<Picture> findByTitleIsNull();
    List<Picture> findAll();

    @Modifying(clearAutomatically = true)
    @Query("update Picture p set p.discontinueDate = getDate() where p.id =:pictureId")
    void deleteById(@Param("pictureId") Long pictureId);

}
