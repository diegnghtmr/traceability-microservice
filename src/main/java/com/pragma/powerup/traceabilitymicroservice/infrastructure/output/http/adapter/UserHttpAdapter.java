package com.pragma.powerup.traceabilitymicroservice.infrastructure.output.http.adapter;

import com.pragma.powerup.traceabilitymicroservice.domain.spi.IUserServicePort;
import com.pragma.powerup.traceabilitymicroservice.infrastructure.output.http.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class UserHttpAdapter implements IUserServicePort {

    private final RestClient restClient;

    public UserHttpAdapter(@Value("${user.service.url:http://localhost:8090/user-api}") String userServiceUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(userServiceUrl)
                .build();
    }

    @Override
    public String getEmployeeNameByEmail(String email) {
        try {
            UserResponseDto user = restClient.get()
                    .uri("/user/email/{email}", email)
                    .retrieve()
                    .body(UserResponseDto.class);
            
            if (user != null) {
                return user.getFirstName() + " " + user.getLastName();
            }
            return email; // Fallback to email if user not found
        } catch (Exception e) {
            return email; // Fallback to email on error
        }
    }
}
