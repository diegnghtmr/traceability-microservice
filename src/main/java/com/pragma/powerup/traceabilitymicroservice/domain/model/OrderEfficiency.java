package com.pragma.powerup.traceabilitymicroservice.domain.model;

public class OrderEfficiency {
    private Long orderId;
    private Long durationInMinutes;

    public OrderEfficiency() {
    }

    public OrderEfficiency(Long orderId, Long durationInMinutes) {
        this.orderId = orderId;
        this.durationInMinutes = durationInMinutes;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Long durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }
}
