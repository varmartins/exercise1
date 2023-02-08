package com.varmartins.exercise1.web.exceptions;

/**
 *
 */
public class PersonNotFoundException extends RuntimeException {

    /**
     *
     */
    public PersonNotFoundException() {
        super();
    }

    /**
     *
     * @param message
     * @param cause
     */
    public PersonNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param message
     */
    public PersonNotFoundException(final String message) {
        super(message);
    }

    /**
     *
     * @param cause
     */
    public PersonNotFoundException(final Throwable cause) {
        super(cause);
    }
}