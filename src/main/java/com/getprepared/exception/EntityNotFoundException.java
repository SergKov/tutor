package com.getprepared.exception;

/**
 * Created by koval on 08.01.2017.
 */
public class EntityNotFoundException extends Exception {

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }
}
