package com.pragma.powerup.traceabilitymicroservice.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TraceRequestDto {
    @NotNull
    private Long orderId;
    @NotNull
    private Long clientIdentity;
    @NotNull
    private Long employeeIdentity;
    @NotBlank
    private String previousState;
    @NotBlank
    private String newState;
}
