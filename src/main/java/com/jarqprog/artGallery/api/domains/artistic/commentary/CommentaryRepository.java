package com.jarqprog.artGallery.api.domains.artistic.commentary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Set;

@Repository
@EnableTransactionManagement
public interface CommentaryRepository extends JpaRepository<CommentaryEntity, Long> {

    Set<CommentaryEntity> findAllCommentaryByPictureEntityId(long pictureEntityId);
    Set<CommentaryEntity> findAllCommentaryByUserLogin(String userLogin);
    void deleteById(long id);
    void deleteByPictureEntityId(long pictureEntityId);
    boolean existsByIdAndPictureEntityId(long id, long pictureEntityId);
}
