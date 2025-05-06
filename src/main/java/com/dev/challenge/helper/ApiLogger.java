package com.dev.challenge.helper;

import com.dev.challenge.services.ApiCallLogService;
import org.springframework.stereotype.Component;

@Component
public class ApiLogger {
    private final ApiCallLogService apiCallLogService;

    public ApiLogger(ApiCallLogService apiCallLogService) {
        this.apiCallLogService = apiCallLogService;
    }

    public void logSuccess(String endpoint, String parameters, String response) {
        apiCallLogService.registerApiCall(endpoint, parameters, response, null);
    }

    public void logError(String endpoint, String parameters, String error) {
        apiCallLogService.registerApiCall(endpoint, parameters, null, error);
    }
}