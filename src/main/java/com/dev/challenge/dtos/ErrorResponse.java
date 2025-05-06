package com.dev.challenge.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String details;
    private String path;
    private String timestamp;
}

