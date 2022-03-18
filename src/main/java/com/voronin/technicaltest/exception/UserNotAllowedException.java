package com.voronin.technicaltest.exception;

public class UserNotAllowedException extends RuntimeException {
    public UserNotAllowedException() {
        super();
    }

    public UserNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotAllowedException(String message) {
        super(message);
    }

    public UserNotAllowedException(Throwable cause) {
        super(cause);
    }
}
