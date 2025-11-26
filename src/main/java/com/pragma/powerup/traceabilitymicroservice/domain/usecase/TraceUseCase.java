package com.pragma.powerup.traceabilitymicroservice.domain.usecase;

import com.pragma.powerup.traceabilitymicroservice.domain.api.ITraceServicePort;
import com.pragma.powerup.traceabilitymicroservice.domain.model.Trace;
import com.pragma.powerup.traceabilitymicroservice.domain.spi.ITracePersistencePort;
import java.time.LocalDateTime;
import java.util.List;

public class TraceUseCase implements ITraceServicePort {

    private final ITracePersistencePort tracePersistencePort;

    public TraceUseCase(ITracePersistencePort tracePersistencePort) {
        this.tracePersistencePort = tracePersistencePort;
    }

    @Override
    public Trace createTrace(Trace trace) {
        validateTrace(trace);
        trace.setDate(LocalDateTime.now());
        return tracePersistencePort.saveTrace(trace);
    }

    @Override
    public List<Trace> getTracesByOrderId(Long orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order id is required");
        }
        return tracePersistencePort.getTracesByOrderId(orderId);
    }

    private void validateTrace(Trace trace) {
        if (trace == null) {
            throw new IllegalArgumentException("Trace data is required");
        }
        if (trace.getOrderId() == null) {
            throw new IllegalArgumentException("Order id is required");
        }
        if (trace.getClientIdentity() == null) {
            throw new IllegalArgumentException("Client identity is required");
        }
        if (trace.getEmployeeIdentity() == null) {
            throw new IllegalArgumentException("Employee identity is required");
        }
        if (trace.getPreviousState() == null || trace.getPreviousState().isBlank()) {
            throw new IllegalArgumentException("Previous state is required");
        }
        if (trace.getNewState() == null || trace.getNewState().isBlank()) {
            throw new IllegalArgumentException("New state is required");
        }
    }
}
