package com.pragma.powerup.traceabilitymicroservice.infrastructure.output.mongo.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "trace")
public class TraceEntity {
    @Id
    private String id;
    private Long orderId;
    private Long clientIdentity;
    private Long employeeIdentity;
    private String previousState;
    private String newState;
    private LocalDateTime date;
}
