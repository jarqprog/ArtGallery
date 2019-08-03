package com.jarqprog.artGallery.service;

import com.jarqprog.artGallery.domain.DomainEntity;
import com.jarqprog.artGallery.domain.EntityMetadata;
import org.springframework.stereotype.Service;

@Service
public interface EntityMetadataService {

    EntityMetadata create(DomainEntity domainEntity);

}
