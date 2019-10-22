package com.jarqprog.artGallery.springData.exceptions;

import com.jarqprog.artGallery.domain.dto.DTO;

public class InvalidObjectException extends RuntimeException {

    public InvalidObjectException(Class<? extends DTO> clazz, String message) {
        super(String.format("Cannot persist object %s. Cause: %s", clazz.getSimpleName(), message));
    }
}
