package com.jarqprog.artGallery.domain.exception;

import com.jarqprog.artGallery.domain.entity.DomainEntity;

public class ResourceAlreadyExists extends RuntimeException {

    public ResourceAlreadyExists(Class<? extends DomainEntity> clazz, long entityId) {
        super(String.format("Resource %s with ID=%s already exists!", clazz.getSimpleName(), entityId));
    }
}
