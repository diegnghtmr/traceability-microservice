package com.pragma.powerup.traceabilitymicroservice.application.handler;

import com.pragma.powerup.traceabilitymicroservice.application.dto.response.EmployeeEfficiencyDto;
import com.pragma.powerup.traceabilitymicroservice.application.dto.response.OrderEfficiencyDto;
import com.pragma.powerup.traceabilitymicroservice.application.mapper.IEfficiencyMapper;
import com.pragma.powerup.traceabilitymicroservice.domain.api.ITraceServicePort;
import com.pragma.powerup.traceabilitymicroservice.domain.spi.IUserServicePort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TraceEfficiencyHandler implements ITraceEfficiencyHandler {

    private final ITraceServicePort traceServicePort;
    private final IEfficiencyMapper efficiencyMapper;
    private final IUserServicePort userServicePort;

    @Override
    public List<OrderEfficiencyDto> getOrderEfficiencyReport() {
        return efficiencyMapper.toOrderEfficiencyDtoList(traceServicePort.getOrderEfficiencyReport());
    }

    @Override
    public List<EmployeeEfficiencyDto> getEmployeeEfficiencyReport() {
        List<EmployeeEfficiencyDto> dtos = efficiencyMapper.toEmployeeEfficiencyDtoList(
                traceServicePort.getEmployeeEfficiencyReport()
        );
        
        // Enrich with real employee names from user-microservice
        for (EmployeeEfficiencyDto dto : dtos) {
            String email = dto.getEmployeeName(); // Initially contains email
            String fullName = userServicePort.getEmployeeNameByEmail(email);
            dto.setEmployeeName(fullName);
        }
        
        return dtos;
    }
}
