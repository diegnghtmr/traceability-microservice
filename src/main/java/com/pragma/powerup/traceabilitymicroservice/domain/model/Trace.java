package com.pragma.powerup.traceabilitymicroservice.domain.model;

import java.time.LocalDateTime;

public class Trace {
    private String id;
    private Long orderId;
    private Long clientId;
    private String employeeEmail;
    private String previousState;
    private String newState;
    private LocalDateTime time;

    public Trace() {
    }

    public Trace(String id, Long orderId, Long clientId, String employeeEmail, String previousState, String newState, LocalDateTime time) {
        this.id = id;
        this.orderId = orderId;
        this.clientId = clientId;
        this.employeeEmail = employeeEmail;
        this.previousState = previousState;
        this.newState = newState;
        this.time = time;
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}