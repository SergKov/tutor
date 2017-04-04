package com.getprepared.core.exception;

/**
 * Created by koval on 04.04.2017.
 */
public class QuizNotTerminatedException extends Exception {

    public QuizNotTerminatedException() {
        super();
    }

    public QuizNotTerminatedException(final String message) {
        super(message);
    }

    public QuizNotTerminatedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public QuizNotTerminatedException(final Throwable cause) {
        super(cause);
    }
}
