package com.dropwizard.test.exception;

public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 34678899999l;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public DataNotFoundException(String message) {
        super(message);
    }
}
