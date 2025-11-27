package com.pragma.powerup.traceabilitymicroservice.domain.usecase;

import com.pragma.powerup.traceabilitymicroservice.domain.api.ITraceServicePort;
import com.pragma.powerup.traceabilitymicroservice.domain.exception.UnauthorizedTraceAccessException;
import com.pragma.powerup.traceabilitymicroservice.domain.model.Trace;
import com.pragma.powerup.traceabilitymicroservice.domain.spi.ITracePersistencePort;
import com.pragma.powerup.traceabilitymicroservice.domain.spi.IUserContextPort;
import java.util.List;

public class TraceUseCase implements ITraceServicePort {

    private final ITracePersistencePort tracePersistencePort;
    private final IUserContextPort userContextPort;

    public TraceUseCase(ITracePersistencePort tracePersistencePort, IUserContextPort userContextPort) {
        this.tracePersistencePort = tracePersistencePort;
        this.userContextPort = userContextPort;
    }

    @Override
    public void saveTrace(Trace trace) {
        tracePersistencePort.saveTrace(trace);
    }

    @Override
    public List<Trace> getTraceByClient(Long clientId) {
        Long currentUserId = userContextPort.getCurrentUserId();
        if (currentUserId == null || !currentUserId.equals(clientId)) {
             throw new UnauthorizedTraceAccessException();
        }
        return tracePersistencePort.getTraceByClient(clientId);
    }
}