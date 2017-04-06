package com.getprepared.persistence.exception;

/**
 * Created by koval on 29.01.2017.
 */
public class TransactionalException extends RuntimeException {

    public TransactionalException() { }

    public TransactionalException(final String message) {
        super(message);
    }

    public TransactionalException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TransactionalException(final Throwable cause) {
        super(cause);
    }
}
