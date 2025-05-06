package com.dev.challenge.exceptions;

import com.dev.challenge.dtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "Internal error",
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(CalculationException.class)
    public ResponseEntity<ErrorResponse> handleCalculationException(CalculationException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "Calculation failed",
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String errorDetails = "Invalid parameter value: " + ex.getName() + " should be of type " + ex.getRequiredType().getName();
        ErrorResponse errorResponse = new ErrorResponse(
                "Bad Request",
                errorDetails,
                "path/to/endpoint",
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ApiCallLogException.class)
    public ResponseEntity<ErrorResponse> handleApiCallLogException(ApiCallLogException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "Error while handling API call logs",
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(PercentageServiceException.class)
    public ResponseEntity<ErrorResponse> handlePercentageServiceException(PercentageServiceException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "Percentage service failed",
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
