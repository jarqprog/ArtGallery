package com.jarqprog.artGallery.repository;

import com.jarqprog.artGallery.domain.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Repository
@EnableTransactionManagement
public interface CommentaryRepository extends JpaRepository<Commentary, Long>  {

    List<Commentary> findAllCommentaryByPictureId(long pictureId);

    @Override
    @Modifying
    @Query("delete from Commentary c where c.id = ?1")
    void deleteById(Long id);
}
