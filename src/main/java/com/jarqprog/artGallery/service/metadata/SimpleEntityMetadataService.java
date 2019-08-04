package com.jarqprog.artGallery.service.metadata;

import com.jarqprog.artGallery.domain.DomainEntity;
import com.jarqprog.artGallery.domain.EntityMetadata;
import com.jarqprog.artGallery.repository.EntityMetadataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SimpleEntityMetadataService implements EntityMetadataService {

    @Autowired
    private EntityMetadataRepository entityMetadataRepository;

    @Override
    public EntityMetadata createMetadata(DomainEntity domainEntity) {
        return entityMetadataRepository.save(new EntityMetadata(domainEntity));
    }

    @Override
    public EntityMetadata markDiscontinued(DomainEntity domainEntity) {
        String entity = domainEntity.toString();
        return entityMetadataRepository.save(new EntityMetadata(domainEntity, entity));
    }
}
