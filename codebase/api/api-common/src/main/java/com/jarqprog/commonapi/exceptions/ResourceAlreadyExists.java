package com.jarqprog.commonapi.exceptions;


import com.jarqprog.commonapi.absmodel.DomainEntity;

public class ResourceAlreadyExists extends RuntimeException {

    public ResourceAlreadyExists(Class<? extends DomainEntity> clazz, long entityId) {
        super(String.format("Resource %s with ID=%s already exists!", clazz.getSimpleName(), entityId));
    }
}
