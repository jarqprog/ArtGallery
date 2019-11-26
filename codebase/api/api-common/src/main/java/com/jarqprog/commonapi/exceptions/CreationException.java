package com.jarqprog.commonapi.exceptions;


import com.jarqprog.commondomain.absmodel.Identity;

public class CreationException extends RuntimeException {

    public CreationException(Class<? extends Identity> clazz, String message) {
        super(String.format("Cannot create %s. Cause: %s", clazz.getSimpleName(), message));
    }
}
