package com.jarqprog.artGallery.springData.exceptions;

import com.jarqprog.artGallery.domain.entity.DomainEntity;

import javax.persistence.EntityNotFoundException;

public class ResourceNotFoundException extends EntityNotFoundException {

    public ResourceNotFoundException(Class<? extends DomainEntity> clazz, long entityId) {
        super(String.format("Resource %s with ID=%s not found", clazz.getSimpleName(), entityId));
    }

    public ResourceNotFoundException(Class<? extends DomainEntity> clazz) {
        super(String.format("Resource %s not found", clazz.getSimpleName()));
    }
}
