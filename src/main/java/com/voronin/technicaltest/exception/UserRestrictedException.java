package com.voronin.technicaltest.exception;

public class UserRestrictedException extends RuntimeException {
    public UserRestrictedException() {
        super();
    }

    public UserRestrictedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRestrictedException(String message) {
        super(message);
    }

    public UserRestrictedException(Throwable cause) {
        super(cause);
    }
}
