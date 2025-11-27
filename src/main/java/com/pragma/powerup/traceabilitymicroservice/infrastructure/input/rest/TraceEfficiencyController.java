package com.pragma.powerup.traceabilitymicroservice.infrastructure.input.rest;

import com.pragma.powerup.traceabilitymicroservice.application.dto.response.EmployeeEfficiencyDto;
import com.pragma.powerup.traceabilitymicroservice.application.dto.response.OrderEfficiencyDto;
import com.pragma.powerup.traceabilitymicroservice.application.handler.ITraceEfficiencyHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trace/efficiency")
@RequiredArgsConstructor
public class TraceEfficiencyController {

    private final ITraceEfficiencyHandler traceEfficiencyHandler;

    @Operation(summary = "Get order efficiency report")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Report retrieved", content = @Content(schema = @Schema(implementation = OrderEfficiencyDto.class))),
        @ApiResponse(responseCode = "403", description = "Unauthorized access", content = @Content)
    })
    @GetMapping("/orders")
    @PreAuthorize("hasRole('OWNER')")
    public List<OrderEfficiencyDto> getOrderEfficiencyReport() {
        return traceEfficiencyHandler.getOrderEfficiencyReport();
    }

    @Operation(summary = "Get employee efficiency ranking")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Ranking retrieved", content = @Content(schema = @Schema(implementation = EmployeeEfficiencyDto.class))),
        @ApiResponse(responseCode = "403", description = "Unauthorized access", content = @Content)
    })
    @GetMapping("/employees")
    @PreAuthorize("hasRole('OWNER')")
    public List<EmployeeEfficiencyDto> getEmployeeEfficiencyReport() {
        return traceEfficiencyHandler.getEmployeeEfficiencyReport();
    }
}
