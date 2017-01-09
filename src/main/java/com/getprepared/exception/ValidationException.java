package com.getprepared.exception;

/**
 * Created by koval on 09.01.2017.
 */
public class ValidationException extends Exception {

    public ValidationException() { }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }
}
