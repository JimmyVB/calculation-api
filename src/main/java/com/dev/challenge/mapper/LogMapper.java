package com.dev.challenge.mapper;

import com.dev.challenge.dtos.ApiCallLogDto;
import com.dev.challenge.entities.ApiCallLog;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LogMapper {

    private final ModelMapper modelMapper;

    public LogMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ApiCallLogDto toDto(ApiCallLog apiCallLog) {
        return modelMapper.map(apiCallLog, ApiCallLogDto.class);
    }

    public ApiCallLog toEntity(ApiCallLogDto apiCallLogDto) {
        return modelMapper.map(apiCallLogDto, ApiCallLog.class);
    }
}

