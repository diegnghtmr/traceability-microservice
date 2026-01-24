package com.pragma.powerup.traceabilitymicroservice.domain.model;

public class EmployeeEfficiency {
    private String employeeEmail;
    private Integer completedOrders;
    private Double averageDurationInMinutes;
    private Double efficiency;

    public EmployeeEfficiency() {
    }

    public EmployeeEfficiency(String employeeEmail, Integer completedOrders, Double averageDurationInMinutes, Double efficiency) {
        this.employeeEmail = employeeEmail;
        this.completedOrders = completedOrders;
        this.averageDurationInMinutes = averageDurationInMinutes;
        this.efficiency = efficiency;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Integer getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(Integer completedOrders) {
        this.completedOrders = completedOrders;
    }

    public Double getAverageDurationInMinutes() {
        return averageDurationInMinutes;
    }

    public void setAverageDurationInMinutes(Double averageDurationInMinutes) {
        this.averageDurationInMinutes = averageDurationInMinutes;
    }

    public Double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Double efficiency) {
        this.efficiency = efficiency;
    }
}
