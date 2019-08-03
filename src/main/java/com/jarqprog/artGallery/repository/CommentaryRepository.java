package com.jarqprog.artGallery.repository;

import com.jarqprog.artGallery.domain.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Long>  {
}
