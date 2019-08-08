package com.jarqprog.artGallery.exception.persistenceException;

import com.jarqprog.artGallery.domain.DomainEntity;

public class ResourceAlreadyExists extends RuntimeException {

    public ResourceAlreadyExists(Class<? extends DomainEntity> clazz, long entityId) {
        super(String.format("Resource %s with ID=%s already exists!", clazz.getSimpleName(), entityId));
    }
}
