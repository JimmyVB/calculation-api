package com.dev.challenge.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
@Setter
@Table(name = "api_call_logs")
public class ApiCallLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String endpoint;
    private String parameters;
    private String response;
    private String error;
    private LocalDateTime timestamp;
}

