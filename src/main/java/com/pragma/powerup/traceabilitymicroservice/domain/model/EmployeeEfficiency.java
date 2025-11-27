package com.pragma.powerup.traceabilitymicroservice.domain.model;

public class EmployeeEfficiency {
    private String employeeEmail;
    private Double averageDurationInMinutes;

    public EmployeeEfficiency() {
    }

    public EmployeeEfficiency(String employeeEmail, Double averageDurationInMinutes) {
        this.employeeEmail = employeeEmail;
        this.averageDurationInMinutes = averageDurationInMinutes;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Double getAverageDurationInMinutes() {
        return averageDurationInMinutes;
    }

    public void setAverageDurationInMinutes(Double averageDurationInMinutes) {
        this.averageDurationInMinutes = averageDurationInMinutes;
    }
}
