package com.pragma.powerup.traceabilitymicroservice.infrastructure.security;

import com.pragma.powerup.traceabilitymicroservice.domain.spi.IUserContextPort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserContextAdapter implements IUserContextPort {
    
    private final JwtProvider jwtProvider;

    public UserContextAdapter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getCredentials() instanceof String) {
            String token = (String) authentication.getCredentials();
            return jwtProvider.extractId(token);
        }
        return null;
    }
}
