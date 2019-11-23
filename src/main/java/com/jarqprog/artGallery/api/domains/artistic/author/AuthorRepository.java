package com.jarqprog.artGallery.api.domains.artistic.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@Repository
@EnableTransactionManagement
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    Optional<AuthorEntity> findAuthorByContactId(long contactId);

}
