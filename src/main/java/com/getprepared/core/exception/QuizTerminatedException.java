package com.getprepared.core.exception;

/**
 * Created by koval on 30.03.2017.
 */
public class QuizTerminatedException extends Exception {

    public QuizTerminatedException() {
        super();
    }

    public QuizTerminatedException(final String message) {
        super(message);
    }

    public QuizTerminatedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public QuizTerminatedException(final Throwable cause) {
        super(cause);
    }
}
