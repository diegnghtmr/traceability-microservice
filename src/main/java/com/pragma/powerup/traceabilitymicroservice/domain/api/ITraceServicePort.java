package com.pragma.powerup.traceabilitymicroservice.domain.api;

import com.pragma.powerup.traceabilitymicroservice.domain.model.Trace;
import java.util.List;

public interface ITraceServicePort {
    void saveTrace(Trace trace);
    List<Trace> getTraceByClient(Long clientId);
}