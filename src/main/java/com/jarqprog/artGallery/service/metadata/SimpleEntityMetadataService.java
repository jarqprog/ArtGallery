package com.jarqprog.artGallery.service.metadata;

import com.jarqprog.artGallery.domain.MetadataSupplier;
import com.jarqprog.artGallery.domain.EntityMetadata;
import com.jarqprog.artGallery.repository.EntityMetadataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SimpleEntityMetadataService implements EntityMetadataService {

    @Autowired
    private EntityMetadataRepository entityMetadataRepository;

    @Override
    public EntityMetadata createMetadata(MetadataSupplier metadataSupplier) {
        return entityMetadataRepository.save(new EntityMetadata(metadataSupplier));
    }

    @Override
    public EntityMetadata markDiscontinued(MetadataSupplier metadataSupplier) {
        String entity = metadataSupplier.toString();
        return entityMetadataRepository.save(new EntityMetadata(metadataSupplier, entity));
    }
}
