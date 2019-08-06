package com.jarqprog.artGallery.exception.persistenceException;

import com.jarqprog.artGallery.domain.DomainEntity;

public class CannotFindEntityException extends RuntimeException implements PersistenceException {

    public CannotFindEntityException(Class<? extends DomainEntity> clazz, long id) {
        super(String.format("Cannot find entity %s with id=%s", clazz.getSimpleName(), id));
    }
}
