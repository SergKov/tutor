package com.getprepared.core.exception;

/**
 * Created by koval on 08.01.2017.
 */
public class EntityNotFoundException extends Exception {

    public EntityNotFoundException() { }

    public EntityNotFoundException(final String message) {
        super(message);
    }

    public EntityNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(final Throwable cause) {
        super(cause);
    }
}
