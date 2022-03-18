package com.voronin.technicaltest.exception;

public class UserConflictException extends RuntimeException {
    public UserConflictException() {
        super();
    }

    public UserConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserConflictException(String message) {
        super(message);
    }

    public UserConflictException(Throwable cause) {
        super(cause);
    }
}
