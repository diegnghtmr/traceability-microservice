package com.pragma.powerup.traceabilitymicroservice.domain.usecase;

import com.pragma.powerup.traceabilitymicroservice.domain.exception.UnauthorizedTraceAccessException;
import com.pragma.powerup.traceabilitymicroservice.domain.model.Trace;
import com.pragma.powerup.traceabilitymicroservice.domain.spi.ITracePersistencePort;
import com.pragma.powerup.traceabilitymicroservice.domain.spi.IUserContextPort;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TraceUseCaseTest {

    @Mock
    private ITracePersistencePort tracePersistencePort;

    @Mock
    private IUserContextPort userContextPort;

    @InjectMocks
    private TraceUseCase traceUseCase;

    @Test
    void saveTraceShouldDelegateToPersistence() {
        Trace trace = new Trace(null, 1L, 10L, "employee@test.com", "CREATED", "READY", LocalDateTime.now(), 20L);
        
        traceUseCase.saveTrace(trace);

        ArgumentCaptor<Trace> traceCaptor = ArgumentCaptor.forClass(Trace.class);
        verify(tracePersistencePort).saveTrace(traceCaptor.capture());
        assertEquals(1L, traceCaptor.getValue().getOrderId());
        assertEquals(10L, traceCaptor.getValue().getClientId());
        assertEquals("employee@test.com", traceCaptor.getValue().getEmployeeEmail());
        assertEquals("CREATED", traceCaptor.getValue().getPreviousState());
        assertEquals("READY", traceCaptor.getValue().getNewState());
        assertEquals(20L, traceCaptor.getValue().getRestaurantId());
    }

    @Test
    void getTraceByClientShouldReturnPersistenceResultWhenAuthorized() {
        Long clientId = 10L;
        List<Trace> traces = List.of(new Trace("1", 2L, clientId, "employee@test.com", "PENDING", "DONE", LocalDateTime.now(), 22L));
        
        when(userContextPort.getCurrentUserId()).thenReturn(clientId);
        when(tracePersistencePort.getTraceByClient(clientId)).thenReturn(traces);

        List<Trace> result = traceUseCase.getTraceByClient(clientId);

        assertEquals(traces, result);
        verify(tracePersistencePort).getTraceByClient(clientId);
    }

    @Test
    void getTraceByClientShouldThrowExceptionWhenUnauthorized() {
        Long clientId = 10L;
        Long differentUserId = 99L;
        
        when(userContextPort.getCurrentUserId()).thenReturn(differentUserId);

        assertThrows(UnauthorizedTraceAccessException.class, () -> traceUseCase.getTraceByClient(clientId));
    }

    @Test
    void getTraceByClientShouldThrowExceptionWhenUserIdIsNull() {
        Long clientId = 10L;
        
        when(userContextPort.getCurrentUserId()).thenReturn(null);

        assertThrows(UnauthorizedTraceAccessException.class, () -> traceUseCase.getTraceByClient(clientId));
    }
}
