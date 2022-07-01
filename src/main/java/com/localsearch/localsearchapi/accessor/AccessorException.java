package com.localsearch.localsearchapi.accessor;

import org.springframework.http.HttpStatus;

public class AccessorException extends RuntimeException {

    private final HttpStatus httpStatus;

    public AccessorException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
