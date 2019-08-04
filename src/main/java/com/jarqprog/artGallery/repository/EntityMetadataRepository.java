package com.jarqprog.artGallery.repository;

import com.jarqprog.artGallery.domain.EntityMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Repository
@EnableTransactionManagement
public interface EntityMetadataRepository extends JpaRepository<EntityMetadata, Long> {
}
