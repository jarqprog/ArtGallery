package com.jarqprog.artGallery.service.metadata;

import com.jarqprog.artGallery.domain.MetadataSupplier;
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
    public EntityMetadata create(MetadataSupplier metadataSupplier) {
        return entityMetadataRepository.save(new EntityMetadata(metadataSupplier));
    }

    @Override
    public EntityMetadata markDiscontinued(MetadataSupplier metadataSupplier) {
        //todo we can specify which data to store (for now it's just toString())
        String removedEntityData = metadataSupplier.toString();
        return entityMetadataRepository.save(new EntityMetadata(metadataSupplier, removedEntityData));
    }
}
