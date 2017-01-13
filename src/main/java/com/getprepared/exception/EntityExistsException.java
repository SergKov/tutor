package com.getprepared.exception;

/**
 * Created by koval on 13.01.2017.
 */
public class EntityExistsException extends Exception {

    public EntityExistsException() { }

    public EntityExistsException(String message) {
        super(message);
    }

    public EntityExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityExistsException(Throwable cause) {
        super(cause);
    }
}
