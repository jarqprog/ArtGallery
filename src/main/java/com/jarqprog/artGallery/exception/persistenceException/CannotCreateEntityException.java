package com.jarqprog.artGallery.exception.persistenceException;

import com.jarqprog.artGallery.domain.DomainEntity;

public class CannotCreateEntityException extends RuntimeException implements PersistenceException {

    public CannotCreateEntityException(Class<? extends DomainEntity> clazz, long id, PersistenceException rootCause) {
        super(String.format("Cannot create entity %s with id=%s. Root cause: ", clazz.getSimpleName(), id) + rootCause.getMessage());
    }

    public CannotCreateEntityException(Class<? extends DomainEntity> clazz, long id, String rootCause) {
        super(String.format("Cannot create entity %s with id=%s. Root cause: ", clazz.getSimpleName(), id) + rootCause);
    }
}


