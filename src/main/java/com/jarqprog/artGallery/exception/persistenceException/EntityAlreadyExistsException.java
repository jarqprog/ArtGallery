package com.jarqprog.artGallery.exception.persistenceException;

import com.jarqprog.artGallery.domain.DomainEntity;

public class EntityAlreadyExistsException extends RuntimeException implements PersistenceException {

     public EntityAlreadyExistsException(Class<? extends DomainEntity> clazz, long id) {
        super(String.format("Cannot create entity. Entity %s with id=%s already exists!", clazz.getSimpleName(), id));
    }
}
