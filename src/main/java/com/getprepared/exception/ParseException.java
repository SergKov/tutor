package com.getprepared.exception;

/**
 * Created by koval on 19.01.2017.
 */
public class ParseException extends Exception {

    public ParseException() { }

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseException(Throwable cause) {
        super(cause);
    }
}
