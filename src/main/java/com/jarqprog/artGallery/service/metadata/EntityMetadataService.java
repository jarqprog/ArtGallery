package com.jarqprog.artGallery.service.metadata;

import com.jarqprog.artGallery.domain.DomainEntity;
import com.jarqprog.artGallery.domain.EntityMetadata;
import org.springframework.stereotype.Service;

@Service
public interface EntityMetadataService {

    EntityMetadata createMetadata(DomainEntity domainEntity);
    EntityMetadata markDiscontinued(DomainEntity domainEntity);

}
