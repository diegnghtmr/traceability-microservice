package com.pragma.powerup.traceabilitymicroservice.infrastructure.output.mongo.repository;

import com.pragma.powerup.traceabilitymicroservice.infrastructure.output.mongo.entity.TraceEntity;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ITraceRepository extends MongoRepository<TraceEntity, String> {
    List<TraceEntity> findAllByClientId(Long clientId);
    List<TraceEntity> findAllByRestaurantId(Long restaurantId);
}
