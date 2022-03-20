package com.voronin.technicaltest.exception;

/**
 * The type User restricted exception.
 */
public class UserRestrictedException extends RuntimeException {
    /**
     * Instantiates a new User restricted exception.
     */
    public UserRestrictedException() {
        super();
    }

    /**
     * Instantiates a new User restricted exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public UserRestrictedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new User restricted exception.
     *
     * @param message the message
     */
    public UserRestrictedException(String message) {
        super(message);
    }

    /**
     * Instantiates a new User restricted exception.
     *
     * @param cause the cause
     */
    public UserRestrictedException(Throwable cause) {
        super(cause);
    }
    
}
