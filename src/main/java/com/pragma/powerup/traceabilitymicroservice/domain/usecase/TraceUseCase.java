package com.pragma.powerup.traceabilitymicroservice.domain.usecase;

import com.pragma.powerup.traceabilitymicroservice.domain.api.ITraceServicePort;
import com.pragma.powerup.traceabilitymicroservice.domain.exception.UnauthorizedTraceAccessException;
import com.pragma.powerup.traceabilitymicroservice.domain.model.EmployeeEfficiency;
import com.pragma.powerup.traceabilitymicroservice.domain.model.OrderEfficiency;
import com.pragma.powerup.traceabilitymicroservice.domain.model.Trace;
import com.pragma.powerup.traceabilitymicroservice.domain.spi.ITracePersistencePort;
import com.pragma.powerup.traceabilitymicroservice.domain.spi.IUserContextPort;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public List<OrderEfficiency> getOrderEfficiencyReport() {
        Long restaurantId = userContextPort.getRestaurantId();
        if (restaurantId == null) {
            throw new UnauthorizedTraceAccessException();
        }

        List<Trace> traces = tracePersistencePort.getAllTracesByRestaurant(restaurantId);
        Map<Long, List<Trace>> tracesByOrder = traces.stream()
                .collect(Collectors.groupingBy(Trace::getOrderId));

        List<OrderEfficiency> report = new ArrayList<>();

        for (Map.Entry<Long, List<Trace>> entry : tracesByOrder.entrySet()) {
            Long orderId = entry.getKey();
            List<Trace> orderTraces = entry.getValue();

            LocalDateTime startTime = getStartTime(orderTraces);
            LocalDateTime endTime = getEndTime(orderTraces);

            if (startTime != null && endTime != null) {
                long duration = Duration.between(startTime, endTime).toMinutes();
                report.add(new OrderEfficiency(orderId, duration));
            }
        }

        return report;
    }

    @Override
    public List<EmployeeEfficiency> getEmployeeEfficiencyReport() {
        Long restaurantId = userContextPort.getRestaurantId();
        if (restaurantId == null) {
            throw new UnauthorizedTraceAccessException();
        }

        List<Trace> traces = tracePersistencePort.getAllTracesByRestaurant(restaurantId);
        Map<Long, List<Trace>> tracesByOrder = traces.stream()
                .collect(Collectors.groupingBy(Trace::getOrderId));

        // Map<EmployeeEmail, List<Duration>>
        Map<String, List<Long>> durationsByEmployee = new java.util.HashMap<>();

        for (List<Trace> orderTraces : tracesByOrder.values()) {
            String employeeEmail = getEmployeeEmail(orderTraces);
            if (employeeEmail == null) continue;

            LocalDateTime startTime = getStartTime(orderTraces);
            LocalDateTime endTime = getEndTime(orderTraces);

            if (startTime != null && endTime != null) {
                long duration = Duration.between(startTime, endTime).toMinutes();
                durationsByEmployee.computeIfAbsent(employeeEmail, k -> new ArrayList<>()).add(duration);
            }
        }

        List<EmployeeEfficiency> ranking = new ArrayList<>();
        for (Map.Entry<String, List<Long>> entry : durationsByEmployee.entrySet()) {
            double average = entry.getValue().stream().mapToLong(Long::longValue).average().orElse(0.0);
            ranking.add(new EmployeeEfficiency(entry.getKey(), average));
        }

        return ranking.stream()
                .sorted(Comparator.comparingDouble(EmployeeEfficiency::getAverageDurationInMinutes))
                .collect(Collectors.toList());
    }

    private LocalDateTime getStartTime(List<Trace> traces) {
        return traces.stream()
                .filter(t -> "PENDIENTE".equals(t.getNewState()) || (t.getPreviousState() == null && "PENDIENTE".equals(t.getNewState())))
                .findFirst()
                .map(Trace::getTime)
                .orElse(null);
    }

    private LocalDateTime getEndTime(List<Trace> traces) {
        return traces.stream()
                .filter(t -> "ENTREGADO".equals(t.getNewState()))
                .findFirst()
                .map(Trace::getTime)
                .orElse(null);
    }

    private String getEmployeeEmail(List<Trace> traces) {
        return traces.stream()
                .filter(t -> "EN_PREPARACION".equals(t.getNewState()))
                .findFirst()
                .map(Trace::getEmployeeEmail)
                .orElse(null);
    }
}
