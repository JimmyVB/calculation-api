package com.dev.challenge.exceptions;

public class PercentageServiceException extends RuntimeException {

    public PercentageServiceException(String message) {
        super(message);
    }

    public PercentageServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}