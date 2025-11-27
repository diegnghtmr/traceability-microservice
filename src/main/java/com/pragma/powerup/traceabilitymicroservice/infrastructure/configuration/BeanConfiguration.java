package com.pragma.powerup.traceabilitymicroservice.infrastructure.configuration;

import com.pragma.powerup.traceabilitymicroservice.domain.api.ITraceServicePort;
import com.pragma.powerup.traceabilitymicroservice.domain.spi.ITracePersistencePort;
import com.pragma.powerup.traceabilitymicroservice.domain.spi.IUserContextPort;
import com.pragma.powerup.traceabilitymicroservice.domain.usecase.TraceUseCase;
import com.pragma.powerup.traceabilitymicroservice.infrastructure.output.mongo.adapter.TraceMongoAdapter;
import com.pragma.powerup.traceabilitymicroservice.infrastructure.output.mongo.mapper.ITraceEntityMapper;
import com.pragma.powerup.traceabilitymicroservice.infrastructure.output.mongo.repository.ITraceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ITraceRepository traceRepository;
    private final ITraceEntityMapper traceEntityMapper;
    private final IUserContextPort userContextPort;

    @Bean
    public ITracePersistencePort tracePersistencePort() {
        return new TraceMongoAdapter(traceRepository, traceEntityMapper);
    }

    @Bean
    public ITraceServicePort traceServicePort() {
        return new TraceUseCase(tracePersistencePort(), userContextPort);
    }
}