package com.voronin.technicaltest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class UserForbiddenException extends RuntimeException {
    public UserForbiddenException() {
        super();
    }

    public UserForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserForbiddenException(String message) {
        super(message);
    }

    public UserForbiddenException(Throwable cause) {
        super(cause);
    }
}
