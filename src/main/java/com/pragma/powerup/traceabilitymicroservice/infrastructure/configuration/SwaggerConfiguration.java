package com.pragma.powerup.traceabilitymicroservice.infrastructure.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI traceabilityOpenApi() {
        return new OpenAPI().info(new Info()
            .title("Traceability Microservice API")
            .version("1.0.0")
            .description("Audit and order traceability operations backed by MongoDB."));
    }
}
