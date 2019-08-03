package com.jarqprog.artGallery.service.metadata;

import com.jarqprog.artGallery.domain.MetadataSupplier;
import com.jarqprog.artGallery.domain.EntityMetadata;
import org.springframework.stereotype.Service;

@Service
public interface EntityMetadataService {

    EntityMetadata create(MetadataSupplier metadataSupplier);
    EntityMetadata markDiscontinued(MetadataSupplier metadataSupplier);

}
