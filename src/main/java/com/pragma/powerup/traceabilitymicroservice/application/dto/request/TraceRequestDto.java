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
    @NotBlank
    private String orderId;
    @NotNull
    private Long clientId;
    // Optional for client-originated traces; keep optional to allow "N/A"
    private String employeeEmail;
    @NotBlank
    private String previousState;
    @NotBlank
    private String newState;
    @NotNull
    private Long restaurantId;
}
