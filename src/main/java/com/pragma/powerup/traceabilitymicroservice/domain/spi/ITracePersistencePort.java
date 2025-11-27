package com.pragma.powerup.traceabilitymicroservice.domain.spi;

import com.pragma.powerup.traceabilitymicroservice.domain.model.Trace;
import java.util.List;

public interface ITracePersistencePort {
    void saveTrace(Trace trace);
    List<Trace> getTraceByClient(Long clientId);
    List<Trace> getAllTracesByRestaurant(Long restaurantId);
}
