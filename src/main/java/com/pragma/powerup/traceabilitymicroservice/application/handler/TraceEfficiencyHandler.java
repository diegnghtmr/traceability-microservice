package com.pragma.powerup.traceabilitymicroservice.application.handler;

import com.pragma.powerup.traceabilitymicroservice.application.dto.response.EmployeeEfficiencyDto;
import com.pragma.powerup.traceabilitymicroservice.application.dto.response.OrderEfficiencyDto;
import com.pragma.powerup.traceabilitymicroservice.application.mapper.IEfficiencyMapper;
import com.pragma.powerup.traceabilitymicroservice.domain.api.ITraceServicePort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TraceEfficiencyHandler implements ITraceEfficiencyHandler {

    private final ITraceServicePort traceServicePort;
    private final IEfficiencyMapper efficiencyMapper;

    @Override
    public List<OrderEfficiencyDto> getOrderEfficiencyReport() {
        return efficiencyMapper.toOrderEfficiencyDtoList(traceServicePort.getOrderEfficiencyReport());
    }

    @Override
    public List<EmployeeEfficiencyDto> getEmployeeEfficiencyReport() {
        return efficiencyMapper.toEmployeeEfficiencyDtoList(traceServicePort.getEmployeeEfficiencyReport());
    }
}
