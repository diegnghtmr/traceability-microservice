package com.pragma.powerup.traceabilitymicroservice.infrastructure.exception;

import com.pragma.powerup.traceabilitymicroservice.domain.exception.UnauthorizedTraceAccessException;
import java.time.LocalDateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception, WebRequest request) {
        ErrorResponse errorResponse = buildErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(UnauthorizedTraceAccessException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedTraceAccessException(UnauthorizedTraceAccessException exception, WebRequest request) {
        ErrorResponse errorResponse = buildErrorResponse("Unauthorized access to trace", HttpStatus.FORBIDDEN, request);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception exception, WebRequest request) {
        ErrorResponse errorResponse = buildErrorResponse("Unexpected server error", HttpStatus.INTERNAL_SERVER_ERROR, request);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String validationMessage = ex.getBindingResult().getFieldErrors().stream()
            .findFirst()
            .map(FieldError::getDefaultMessage)
            .orElse("Validation failed");
        ErrorResponse errorResponse = buildErrorResponse(validationMessage, HttpStatus.BAD_REQUEST, request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    private ErrorResponse buildErrorResponse(String message, HttpStatus status, WebRequest request) {
        String path = request instanceof ServletWebRequest servletWebRequest
            ? servletWebRequest.getRequest().getRequestURI()
            : "";
        return new ErrorResponse(LocalDateTime.now(), status.value(), message, path);
    }
}