package com.jarqprog.artGallery.exception.persistenceException;

import com.jarqprog.artGallery.domain.DomainEntity;

public class CannotUpdateEntityException extends RuntimeException implements PersistenceException {

    public CannotUpdateEntityException(Class<? extends DomainEntity> clazz, long id, PersistenceException rootCause) {
        super(String.format("Cannot update entity %s with id=%s. Root cause: ", clazz.getSimpleName(), id) + rootCause.getMessage());
    }

    public CannotUpdateEntityException(Class<? extends DomainEntity> clazz, long id, String rootCause) {
        super(String.format("Cannot update entity %s with id=%s. Root cause: ", clazz.getSimpleName(), id) + rootCause);
    }
}
