package com.pragma.powerup.traceabilitymicroservice.application.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TraceResponseDto {
    private String id;
    private Long orderId;
    private Long clientId;
    private String employeeEmail;
    private String previousState;
    private String newState;
    private LocalDateTime time;
}