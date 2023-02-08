package com.varmartins.exercise1.web.exceptions;

/**
 *
 */
public class PersonIdMismatchException extends RuntimeException {

    /**
     *
     */
    public PersonIdMismatchException() {
        super();
    }

    /**
     *
     * @param message
     * @param cause
     */
    public PersonIdMismatchException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param message
     */
    public PersonIdMismatchException(final String message) {
        super(message);
    }

    /**
     *
     * @param cause
     */
    public PersonIdMismatchException(final Throwable cause) {
        super(cause);
    }
}
