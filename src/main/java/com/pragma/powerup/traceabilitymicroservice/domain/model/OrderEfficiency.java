package com.pragma.powerup.traceabilitymicroservice.domain.model;

public class OrderEfficiency {
    private String orderId;
    private Long durationInMinutes;

    public OrderEfficiency() {
    }

    public OrderEfficiency(String orderId, Long durationInMinutes) {
        this.orderId = orderId;
        this.durationInMinutes = durationInMinutes;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Long durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }
}
