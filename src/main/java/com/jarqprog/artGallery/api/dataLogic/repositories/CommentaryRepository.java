package com.jarqprog.artGallery.api.dataLogic.repositories;

import com.jarqprog.artGallery.domain.entity.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Set;

@Repository
@EnableTransactionManagement
public interface CommentaryRepository extends JpaRepository<Commentary, Long>  {

    Set<Commentary> findAllCommentaryByPictureId(long pictureId);
    Set<Commentary> findAllCommentaryByUserId(long userId);
    void deleteById(long id);
    void deleteByPictureId(long pictureId);
    boolean existsByIdAndPictureId(long id, long pictureId);
}
