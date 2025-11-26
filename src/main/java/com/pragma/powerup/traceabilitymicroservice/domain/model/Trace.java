package com.pragma.powerup.traceabilitymicroservice.domain.model;

import java.time.LocalDateTime;

public class Trace {
    private String id;
    private Long orderId;
    private Long clientIdentity;
    private Long employeeIdentity;
    private String previousState;
    private String newState;
    private LocalDateTime date;

    public Trace() {
    }

    public Trace(String id, Long orderId, Long clientIdentity, Long employeeIdentity, String previousState, String newState, LocalDateTime date) {
        this.id = id;
        this.orderId = orderId;
        this.clientIdentity = clientIdentity;
        this.employeeIdentity = employeeIdentity;
        this.previousState = previousState;
        this.newState = newState;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getClientIdentity() {
        return clientIdentity;
    }

    public void setClientIdentity(Long clientIdentity) {
        this.clientIdentity = clientIdentity;
    }

    public Long getEmployeeIdentity() {
        return employeeIdentity;
    }

    public void setEmployeeIdentity(Long employeeIdentity) {
        this.employeeIdentity = employeeIdentity;
    }

    public String getPreviousState() {
        return previousState;
    }

    public void setPreviousState(String previousState) {
        this.previousState = previousState;
    }

    public String getNewState() {
        return newState;
    }

    public void setNewState(String newState) {
        this.newState = newState;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
