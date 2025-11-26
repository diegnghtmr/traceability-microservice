package com.pragma.powerup.traceabilitymicroservice.application.handler;

import com.pragma.powerup.traceabilitymicroservice.application.dto.request.TraceRequestDto;
import com.pragma.powerup.traceabilitymicroservice.application.dto.response.TraceResponseDto;
import com.pragma.powerup.traceabilitymicroservice.application.mapper.ITraceRequestMapper;
import com.pragma.powerup.traceabilitymicroservice.application.mapper.ITraceResponseMapper;
import com.pragma.powerup.traceabilitymicroservice.domain.api.ITraceServicePort;
import com.pragma.powerup.traceabilitymicroservice.domain.model.Trace;
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
    public TraceResponseDto createTrace(TraceRequestDto traceRequestDto) {
        Trace trace = traceRequestMapper.toTrace(traceRequestDto);
        Trace savedTrace = traceServicePort.createTrace(trace);
        return traceResponseMapper.toResponseDto(savedTrace);
    }

    @Override
    public List<TraceResponseDto> getTracesByOrderId(Long orderId) {
        List<Trace> traces = traceServicePort.getTracesByOrderId(orderId);
        return traceResponseMapper.toResponseDtoList(traces);
    }
}
