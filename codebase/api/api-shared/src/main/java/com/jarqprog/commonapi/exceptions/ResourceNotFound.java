package com.jarqprog.commonapi.exceptions;


import com.jarqprog.commonapi.absmodel.DomainEntity;

import javax.persistence.EntityNotFoundException;

public class ResourceNotFound extends EntityNotFoundException {

    public ResourceNotFound(Class<? extends DomainEntity> clazz, long entityId) {
        super(String.format("Resource %s with ID=%s not found", clazz.getSimpleName(), entityId));
    }

    public ResourceNotFound(Class<? extends DomainEntity> clazz) {
        super(String.format("Resource %s not found", clazz.getSimpleName()));
    }
}
