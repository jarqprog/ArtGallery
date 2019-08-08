package com.jarqprog.artGallery.exception.persistenceException;

public abstract class PersistenceException extends RuntimeException {

    //marker class to use in ExceptionHandler

    PersistenceException(String message) {
        super(message);
    }
}
