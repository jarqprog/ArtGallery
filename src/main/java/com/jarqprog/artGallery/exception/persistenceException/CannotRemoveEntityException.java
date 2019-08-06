package com.jarqprog.artGallery.exception.persistenceException;

import com.jarqprog.artGallery.domain.DomainEntity;

public class CannotRemoveEntityException extends RuntimeException implements PersistenceException {

    public CannotRemoveEntityException(Class<? extends DomainEntity> clazz, long id, PersistenceException rootCause) {
        super(String.format("Cannot remove entity %s with id=%s. Root cause: ", clazz.getSimpleName(), id) + rootCause.getMessage());
    }

    public CannotRemoveEntityException(Class<? extends DomainEntity> clazz, long id, String rootCause) {
        super(String.format("Cannot remove entity %s with id=%s. Root cause: ", clazz.getSimpleName(), id) + rootCause);
    }
}
