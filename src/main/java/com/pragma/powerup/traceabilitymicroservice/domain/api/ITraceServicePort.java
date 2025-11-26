package com.pragma.powerup.traceabilitymicroservice.domain.api;

import com.pragma.powerup.traceabilitymicroservice.domain.model.Trace;
import java.util.List;

public interface ITraceServicePort {
    Trace createTrace(Trace trace);

    List<Trace> getTracesByOrderId(Long orderId);
}
