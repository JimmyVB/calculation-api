package com.dev.challenge.exceptions;

public class CalculationException extends RuntimeException {
    public CalculationException(String message) {
        super(message);
    }

    public CalculationException(String message, Throwable cause) {
        super(message, cause);
    }
}