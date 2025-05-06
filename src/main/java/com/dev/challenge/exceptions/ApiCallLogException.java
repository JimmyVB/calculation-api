package com.dev.challenge.exceptions;

public class ApiCallLogException extends RuntimeException {
    public ApiCallLogException(String message) {
        super(message);
    }

    public ApiCallLogException(String message, Throwable cause) {
        super(message, cause);
    }
}
