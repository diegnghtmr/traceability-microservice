package com.pragma.powerup.traceabilitymicroservice.infrastructure.output.mongo.adapter;

import com.pragma.powerup.traceabilitymicroservice.domain.model.Trace;
import com.pragma.powerup.traceabilitymicroservice.domain.spi.ITracePersistencePort;
import com.pragma.powerup.traceabilitymicroservice.infrastructure.output.mongo.entity.TraceEntity;
import com.pragma.powerup.traceabilitymicroservice.infrastructure.output.mongo.mapper.ITraceEntityMapper;
import com.pragma.powerup.traceabilitymicroservice.infrastructure.output.mongo.repository.ITraceRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TraceMongoAdapter implements ITracePersistencePort {

    private final ITraceRepository traceRepository;
    private final ITraceEntityMapper traceEntityMapper;

    @Override
    public void saveTrace(Trace trace) {
        traceRepository.save(traceEntityMapper.toEntity(trace));
    }

    @Override
    public List<Trace> getTraceByClient(Long clientId) {
        List<TraceEntity> traceEntities = traceRepository.findAllByClientId(clientId);
        return traceEntityMapper.toTraceList(traceEntities);
    }

    @Override
    public List<Trace> getAllTracesByRestaurant(Long restaurantId) {
        List<TraceEntity> traceEntities = traceRepository.findAllByRestaurantId(restaurantId);
        return traceEntityMapper.toTraceList(traceEntities);
    }
}
