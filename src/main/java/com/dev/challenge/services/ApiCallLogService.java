package com.dev.challenge.services;

import com.dev.challenge.dtos.ApiCallLogDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApiCallLogService {
   void registerApiCall(String endpoint, String parameters, String response, String error);
    Page<ApiCallLogDto> getApiCallLogs(Pageable pageable);

}
