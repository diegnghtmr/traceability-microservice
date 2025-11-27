package com.pragma.powerup.traceabilitymicroservice.infrastructure.input.rest;

import com.pragma.powerup.traceabilitymicroservice.application.dto.request.TraceRequestDto;
import com.pragma.powerup.traceabilitymicroservice.application.dto.response.TraceResponseDto;
import com.pragma.powerup.traceabilitymicroservice.application.handler.ITraceHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trace")
@Validated
@RequiredArgsConstructor
public class TraceRestController {

    private final ITraceHandler traceHandler;

    @Operation(summary = "Create a new trace log")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Trace created"),
        @ApiResponse(responseCode = "400", description = "Invalid trace data supplied", content = @Content),
        @ApiResponse(responseCode = "500", description = "Unexpected server error", content = @Content)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTrace(@Valid @RequestBody TraceRequestDto traceRequestDto) {
        traceHandler.saveTrace(traceRequestDto);
    }

    @Operation(summary = "Get traces by client id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Traces retrieved", content = @Content(schema = @Schema(implementation = TraceResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Invalid client id", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized access to trace", content = @Content)
    })
    @GetMapping("/client/{clientId}")
    public List<TraceResponseDto> getTraceByClient(@PathVariable Long clientId) {
        return traceHandler.getTraceByClient(clientId);
    }
}