package com.pragma.powerup.traceabilitymicroservice.domain.spi;

import com.pragma.powerup.traceabilitymicroservice.domain.model.Trace;
import java.util.List;

public interface ITracePersistencePort {
    Trace saveTrace(Trace trace);

    List<Trace> getTracesByOrderId(Long orderId);
}
