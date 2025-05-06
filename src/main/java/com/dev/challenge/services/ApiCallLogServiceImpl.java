package com.dev.challenge.services;

import com.dev.challenge.dtos.ApiCallLogDto;
import com.dev.challenge.entities.ApiCallLog;
import com.dev.challenge.exceptions.ApiCallLogException;
import com.dev.challenge.mapper.LogMapper;
import com.dev.challenge.repositories.ApiCallLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ApiCallLogServiceImpl implements ApiCallLogService {

    private final ApiCallLogRepository logRepository;
    private final LogMapper logMapper;

    @Autowired
    public ApiCallLogServiceImpl(ApiCallLogRepository logRepository, LogMapper logMapper) {
        this.logRepository = logRepository;
        this.logMapper = logMapper;
    }

    /**
     * Registers an API call log asynchronously.
     * @param endpoint the endpoint that was called
     * @param parameters the parameters passed to the endpoint
     * @param response the response from the API call
     * @param error any error message (if applicable)
     */
    @Override
    @Async
    public void registerApiCall(String endpoint, String parameters, String response, String error) {
        try {
            ApiCallLog log = new ApiCallLog();
            log.setEndpoint(endpoint);
            log.setParameters(parameters);
            log.setResponse(response);
            log.setError(error);
            log.setTimestamp(LocalDateTime.now());
            logRepository.save(log);
        } catch (DataAccessException e) {
            throw new ApiCallLogException("Error while saving API call log.", e);
        }
    }


    /**
     * Retrieves a paginated list of API call logs.
     * @param pageable the pagination information
     * @return a paginated list of API call logs
     */
    @Override
    public Page<ApiCallLogDto> getApiCallLogs(Pageable pageable) {
        try {
            return logRepository
                    .findAll(pageable)
                    .map(logMapper::toDto);
        } catch (DataAccessException e) {
            throw new ApiCallLogException("Error while retrieving API call logs.", e);
        }
    }
}

