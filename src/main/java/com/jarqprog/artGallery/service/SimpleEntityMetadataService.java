package com.jarqprog.artGallery.service;

import com.jarqprog.artGallery.domain.DomainEntity;
import com.jarqprog.artGallery.domain.EntityMetadata;
import com.jarqprog.artGallery.repository.EntityMetadataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SimpleEntityMetadataService implements EntityMetadataService {

    private final EntityMetadataRepository entityMetadataRepository;

    @Autowired
    public SimpleEntityMetadataService(EntityMetadataRepository entityMetadataRepository) {
        this.entityMetadataRepository = entityMetadataRepository;
    }

    @Override
    public EntityMetadata create(DomainEntity domainEntity) {
        return entityMetadataRepository.save(new EntityMetadata(domainEntity));
    }

}
