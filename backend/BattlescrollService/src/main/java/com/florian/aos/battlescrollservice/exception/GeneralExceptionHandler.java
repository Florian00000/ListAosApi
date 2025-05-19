package com.florian.aos.battlescrollservice.exception;

import com.florian.aos.battlescrollservice.dto.ErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDto> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
        log.error("Integrity constraint error detected", request.getRequestURI(), e.getMessage(), e);

        String errorMessage = e.getMessage();
        if (errorMessage != null && errorMessage.contains("uc_version_name")) {
            return buildErrorResponse(
                    "This Version name is already used", HttpStatus.CONFLICT, request.getRequestURI()
            );
        }
        return buildErrorResponse("Violation of integrity constraints.", HttpStatus.BAD_REQUEST, request.getRequestURI());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> notFoundExceptionHandler (NotFoundException e, HttpServletRequest request){
        log.warn("NotFoundException on URI {} : {}", request.getRequestURI(), e.getMessage());
        return buildErrorResponse(e.getMessage() + " not found", HttpStatus.NOT_FOUND, request.getRequestURI());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        log.warn("IllegalArgumentException: {}", e.getMessage());
        return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST, request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleOtherExceptions(Exception e, HttpServletRequest request) {
        log.error("Erreur inattendue", e);
        return buildErrorResponse("Une erreur interne est survenue.",
                HttpStatus.INTERNAL_SERVER_ERROR, request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleBindErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String message = ex.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(" | "));

        ErrorDto error = new ErrorDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                message,
                request.getRequestURI()
        );

        log.warn("Validation error on URI {}: {}", request.getRequestURI(), message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private ResponseEntity<ErrorDto> buildErrorResponse(String message, HttpStatus status, String path) {
        ErrorDto errorDto = new ErrorDto(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path
        );
        return ResponseEntity.status(status).body(errorDto);
    }
}
