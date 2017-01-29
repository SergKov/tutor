package com.getprepared.exception;

/**
 * Created by koval on 29.01.2017.
 */
public class TransactionalException extends RuntimeException {

    public TransactionalException() { }

    public TransactionalException(String message) {
        super(message);
    }

    public TransactionalException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionalException(Throwable cause) {
        super(cause);
    }
}
