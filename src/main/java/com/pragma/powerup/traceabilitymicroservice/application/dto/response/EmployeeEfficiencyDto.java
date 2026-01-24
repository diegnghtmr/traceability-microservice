package com.pragma.powerup.traceabilitymicroservice.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEfficiencyDto {
    private String employeeName;
    private Integer completedOrders;
    private Double averageCompletionTime;
    private Double efficiency;
}
