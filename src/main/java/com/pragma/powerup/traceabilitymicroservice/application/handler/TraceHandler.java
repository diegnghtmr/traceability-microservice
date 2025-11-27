package com.pragma.powerup.traceabilitymicroservice.application.handler;

import com.pragma.powerup.traceabilitymicroservice.application.dto.request.TraceRequestDto;
import com.pragma.powerup.traceabilitymicroservice.application.dto.response.TraceResponseDto;
import com.pragma.powerup.traceabilitymicroservice.application.mapper.ITraceRequestMapper;
import com.pragma.powerup.traceabilitymicroservice.application.mapper.ITraceResponseMapper;
import com.pragma.powerup.traceabilitymicroservice.domain.api.ITraceServicePort;
import com.pragma.powerup.traceabilitymicroservice.domain.model.Trace;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TraceHandler implements ITraceHandler {

    private final ITraceServicePort traceServicePort;
    private final ITraceRequestMapper traceRequestMapper;
    private final ITraceResponseMapper traceResponseMapper;

    @Override
    public void saveTrace(TraceRequestDto traceRequestDto) {
        Trace trace = traceRequestMapper.toTrace(traceRequestDto);
        trace.setTime(LocalDateTime.now());
        traceServicePort.saveTrace(trace);
    }

    @Override
    public List<TraceResponseDto> getTraceByClient(Long clientId) {
        List<Trace> traces = traceServicePort.getTraceByClient(clientId);
        return traceResponseMapper.toResponseDtoList(traces);
    }
}