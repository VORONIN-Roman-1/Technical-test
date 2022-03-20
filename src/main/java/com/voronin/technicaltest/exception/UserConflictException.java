package com.voronin.technicaltest.exception;

/**
 * The type User conflict exception.
 */
public class UserConflictException extends RuntimeException {
    /**
     * Instantiates a new User conflict exception.
     */
    public UserConflictException() {
        super();
    }

    /**
     * Instantiates a new User conflict exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public UserConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new User conflict exception.
     *
     * @param message the message
     */
    public UserConflictException(String message) {
        super(message);
    }

    /**
     * Instantiates a new User conflict exception.
     *
     * @param cause the cause
     */
    public UserConflictException(Throwable cause) {
        super(cause);
    }
    
}
