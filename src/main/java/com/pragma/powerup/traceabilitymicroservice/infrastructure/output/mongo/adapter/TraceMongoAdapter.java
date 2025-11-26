package com.pragma.powerup.traceabilitymicroservice.infrastructure.output.mongo.adapter;

import com.pragma.powerup.traceabilitymicroservice.domain.model.Trace;
import com.pragma.powerup.traceabilitymicroservice.domain.spi.ITracePersistencePort;
import com.pragma.powerup.traceabilitymicroservice.infrastructure.output.mongo.entity.TraceEntity;
import com.pragma.powerup.traceabilitymicroservice.infrastructure.output.mongo.mapper.ITraceEntityMapper;
import com.pragma.powerup.traceabilitymicroservice.infrastructure.output.mongo.repository.ITraceRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TraceMongoAdapter implements ITracePersistencePort {

    private final ITraceRepository traceRepository;
    private final ITraceEntityMapper traceEntityMapper;

    @Override
    public Trace saveTrace(Trace trace) {
        TraceEntity savedEntity = traceRepository.save(traceEntityMapper.toEntity(trace));
        return traceEntityMapper.toTrace(savedEntity);
    }

    @Override
    public List<Trace> getTracesByOrderId(Long orderId) {
        List<TraceEntity> traceEntities = traceRepository.findByOrderId(orderId);
        return traceEntityMapper.toTraceList(traceEntities);
    }
}
