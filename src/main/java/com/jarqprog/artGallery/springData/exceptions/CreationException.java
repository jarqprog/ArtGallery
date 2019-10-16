package com.jarqprog.artGallery.springData.exceptions;

import com.jarqprog.artGallery.domain.dto.DTO;

public class CreationException extends RuntimeException {

    public CreationException(Class<? extends DTO> clazz, String message) {
        super(String.format("Cannot create %s. Cause: %s", clazz.getSimpleName(), message));
    }
}
