package com.pragma.powerup.traceabilitymicroservice.infrastructure.output.mongo.mapper;

import com.pragma.powerup.traceabilitymicroservice.domain.model.Trace;
import com.pragma.powerup.traceabilitymicroservice.infrastructure.output.mongo.entity.TraceEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITraceEntityMapper {
    TraceEntity toEntity(Trace trace);

    Trace toTrace(TraceEntity traceEntity);

    List<Trace> toTraceList(List<TraceEntity> traceEntities);
}
