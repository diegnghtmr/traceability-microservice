package com.pragma.powerup.traceabilitymicroservice.application.mapper;

import com.pragma.powerup.traceabilitymicroservice.application.dto.request.TraceRequestDto;
import com.pragma.powerup.traceabilitymicroservice.domain.model.Trace;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ITraceRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "time", ignore = true)
    Trace toTrace(TraceRequestDto traceRequestDto);
}