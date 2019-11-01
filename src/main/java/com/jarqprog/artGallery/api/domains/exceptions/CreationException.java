package com.jarqprog.artGallery.api.domains.exceptions;


import com.jarqprog.artGallery.api.domains.DomainDTO;

public class CreationException extends RuntimeException {

    public CreationException(Class<? extends DomainDTO> clazz, String message) {
        super(String.format("Cannot create %s. Cause: %s", clazz.getSimpleName(), message));
    }
}
