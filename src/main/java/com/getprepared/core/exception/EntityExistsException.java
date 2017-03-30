package com.getprepared.core.exception;

/**
 * Created by koval on 13.01.2017.
 */
public class EntityExistsException extends Exception {

    public EntityExistsException() { }

    public EntityExistsException(final String message) {
        super(message);
    }

    public EntityExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public EntityExistsException(final Throwable cause) {
        super(cause);
    }
}
