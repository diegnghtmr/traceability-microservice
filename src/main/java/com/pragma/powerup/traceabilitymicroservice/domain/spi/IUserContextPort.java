package com.pragma.powerup.traceabilitymicroservice.domain.spi;

public interface IUserContextPort {
    Long getCurrentUserId();
    Long getRestaurantId();
}