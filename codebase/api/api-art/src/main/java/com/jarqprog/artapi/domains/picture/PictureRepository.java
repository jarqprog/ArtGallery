package com.jarqprog.artapi.domains.picture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Set;

@Repository
@EnableTransactionManagement
public interface PictureRepository extends JpaRepository<PictureEntity, Long> {

    Set<PictureEntity> findAllPicturesByUserLogin(String userLogin);
    Set<PictureEntity> findAllPicturesByAuthorEntityId(long authorEntityId);
}
