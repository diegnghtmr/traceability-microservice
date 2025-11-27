package com.pragma.powerup.traceabilitymicroservice.application.handler;

import com.pragma.powerup.traceabilitymicroservice.application.dto.response.EmployeeEfficiencyDto;
import com.pragma.powerup.traceabilitymicroservice.application.dto.response.OrderEfficiencyDto;
import java.util.List;

public interface ITraceEfficiencyHandler {
    List<OrderEfficiencyDto> getOrderEfficiencyReport();
    List<EmployeeEfficiencyDto> getEmployeeEfficiencyReport();
}
