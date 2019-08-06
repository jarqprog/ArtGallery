package com.jarqprog.artGallery.repository;

import com.jarqprog.artGallery.domain.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Set;

@Repository
@EnableTransactionManagement
public interface CommentaryRepository extends JpaRepository<Commentary, Long>  {

    Set<Commentary> findAllCommentaryByPictureId(long pictureId);

    void deleteById(Long id);
}
