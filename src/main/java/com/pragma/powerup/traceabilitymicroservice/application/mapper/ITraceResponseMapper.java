package com.pragma.powerup.traceabilitymicroservice.application.mapper;

import com.pragma.powerup.traceabilitymicroservice.application.dto.response.TraceResponseDto;
import com.pragma.powerup.traceabilitymicroservice.domain.model.Trace;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITraceResponseMapper {
    TraceResponseDto toResponseDto(Trace trace);

    List<TraceResponseDto> toResponseDtoList(List<Trace> traces);
}