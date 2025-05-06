package com.dev.challenge.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ApiCallLogDto {
    private String endpoint;
    private String parameters;
    private String response;
    private String error;
    private LocalDateTime timestamp;

}

