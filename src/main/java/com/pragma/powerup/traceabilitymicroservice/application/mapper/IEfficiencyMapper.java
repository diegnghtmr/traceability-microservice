package com.pragma.powerup.traceabilitymicroservice.application.mapper;

import com.pragma.powerup.traceabilitymicroservice.application.dto.response.EmployeeEfficiencyDto;
import com.pragma.powerup.traceabilitymicroservice.application.dto.response.OrderEfficiencyDto;
import com.pragma.powerup.traceabilitymicroservice.domain.model.EmployeeEfficiency;
import com.pragma.powerup.traceabilitymicroservice.domain.model.OrderEfficiency;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEfficiencyMapper {
    OrderEfficiencyDto toOrderEfficiencyDto(OrderEfficiency orderEfficiency);
    List<OrderEfficiencyDto> toOrderEfficiencyDtoList(List<OrderEfficiency> orderEfficiencyList);

    EmployeeEfficiencyDto toEmployeeEfficiencyDto(EmployeeEfficiency employeeEfficiency);
    List<EmployeeEfficiencyDto> toEmployeeEfficiencyDtoList(List<EmployeeEfficiency> employeeEfficiencyList);
}
