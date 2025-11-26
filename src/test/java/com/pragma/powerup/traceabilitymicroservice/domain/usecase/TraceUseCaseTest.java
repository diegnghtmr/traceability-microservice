package com.pragma.powerup.traceabilitymicroservice.domain.usecase;

import com.pragma.powerup.traceabilitymicroservice.domain.model.Trace;
import com.pragma.powerup.traceabilitymicroservice.domain.spi.ITracePersistencePort;
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

    @InjectMocks
    private TraceUseCase traceUseCase;

    @Test
    void createTraceShouldSetDateAndDelegateToPersistence() {
        Trace trace = new Trace(null, 1L, 10L, 20L, "CREATED", "READY", null);
        when(tracePersistencePort.saveTrace(any(Trace.class))).thenAnswer(invocation -> {
            Trace savedTrace = invocation.getArgument(0);
            savedTrace.setId("generated-id");
            return savedTrace;
        });

        Trace result = traceUseCase.createTrace(trace);

        ArgumentCaptor<Trace> traceCaptor = ArgumentCaptor.forClass(Trace.class);
        verify(tracePersistencePort).saveTrace(traceCaptor.capture());
        assertEquals(1L, traceCaptor.getValue().getOrderId());
        assertNotNull(traceCaptor.getValue().getDate());
        assertEquals("generated-id", result.getId());
        assertNotNull(result.getDate());
    }

    @Test
    void getTracesByOrderIdShouldReturnPersistenceResult() {
        List<Trace> traces = List.of(new Trace("1", 2L, 11L, 22L, "PENDING", "DONE", LocalDateTime.now()));
        when(tracePersistencePort.getTracesByOrderId(2L)).thenReturn(traces);

        List<Trace> result = traceUseCase.getTracesByOrderId(2L);

        assertEquals(traces, result);
        verify(tracePersistencePort).getTracesByOrderId(2L);
    }

    @Test
    void createTraceShouldValidateRequiredFields() {
        Trace trace = new Trace();

        assertThrows(IllegalArgumentException.class, () -> traceUseCase.createTrace(trace));
    }

    @Test
    void getTracesByOrderIdShouldRejectNullId() {
        assertThrows(IllegalArgumentException.class, () -> traceUseCase.getTracesByOrderId(null));
    }
}
