package com.pragma.powerup.traceabilitymicroservice.application.handler;

import com.pragma.powerup.traceabilitymicroservice.application.dto.request.TraceRequestDto;
import com.pragma.powerup.traceabilitymicroservice.application.dto.response.TraceResponseDto;
import java.util.List;

public interface ITraceHandler {
    void saveTrace(TraceRequestDto traceRequestDto);

    List<TraceResponseDto> getTraceByClient(Long clientId);
}