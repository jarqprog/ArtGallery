package com.jarqprog.artGallery.repository;

import com.jarqprog.artGallery.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@Repository
@EnableTransactionManagement
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findAuthorByContactId(long contactId);
}
