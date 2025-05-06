package com.dev.challenge.controllers;

import com.dev.challenge.dtos.ApiCallLogDto;
import com.dev.challenge.dtos.CalculationDto;
import com.dev.challenge.exceptions.CalculationException;
import com.dev.challenge.helper.ApiLogger;
import com.dev.challenge.services.ApiCallLogService;
import com.dev.challenge.services.CalculationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Calculation API", description = "API for performing calculations and logging API calls")
public class CalculationController {

    @Autowired
    private CalculationService calculationService;
    @Autowired
    private ApiCallLogService apiCallLogService;
    private final ApiLogger apiLogger;

    @GetMapping("/calculate")
    @Operation(
            summary = "Calculates a result based on two numbers and a percentage",
            description = "This endpoint receives two numbers, applies a configured internal percentage, and returns the result of the calculation."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calculation performed correctly"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<CalculationDto> calculate(
            @Parameter(description = "First number of the calculation", required = true)
            @RequestParam double num1,
            @Parameter(description = "Second number of the calculation", required = true)
            @RequestParam double num2) {

        if (num1 < 0 || num2 < 0) {
            throw new CalculationException("Numbers must be non-negative.");
        }

        String params = "num1=" + num1 + "&num2=" + num2;
        try {
            double result = calculationService.calculateWithPercentage(num1, num2);
            apiLogger.logSuccess("/api/calculate", params, "result=" + result);
            return ResponseEntity.ok(new CalculationDto(result));
        } catch (Exception e) {
            apiLogger.logError("/api/calculate", params, e.getMessage());
            throw e;
        }
    }

    @GetMapping("/logs")
    @Operation(
            summary = "Gets a paginated list of API call logs",
            description = "This endpoint returns a paginated list of API call logs, sorted by descending date."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logs obtained successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<Page<ApiCallLogDto>> getLogs(
            @Parameter(description = "Page number (starting from 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of records per page", example = "10")
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("timestamp").descending());
        Page<ApiCallLogDto> logs = apiCallLogService.getApiCallLogs(pageable);
        return ResponseEntity.ok(logs);
    }
}
