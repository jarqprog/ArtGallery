package com.jarqprog.commonapi.exceptions;


import com.jarqprog.commondomain.absmodel.DomainDTO;

public class CreationException extends RuntimeException {

    public CreationException(Class<? extends DomainDTO> clazz, String message) {
        super(String.format("Cannot create %s. Cause: %s", clazz.getSimpleName(), message));
    }
}
