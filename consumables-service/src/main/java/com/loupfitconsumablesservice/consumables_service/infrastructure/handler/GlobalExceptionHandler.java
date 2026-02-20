package com.loupfitconsumablesservice.consumables_service.infrastructure.handler;


import com.loupfitconsumablesservice.consumables_service.business.record.ApiError;
import com.loupfitconsumablesservice.consumables_service.infrastructure.exceptions.ConflictException;
import com.loupfitconsumablesservice.consumables_service.infrastructure.exceptions.ForbiddenException;
import com.loupfitconsumablesservice.consumables_service.infrastructure.exceptions.ResourceNotFoundException;
import com.loupfitconsumablesservice.consumables_service.infrastructure.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handlerResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND.value())
                .body(new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiError> handlerConflictException(ConflictException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiError(ex.getMessage(), HttpStatus.CONFLICT.value()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiError> handlerUnauthorizedException(UnauthorizedException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ApiError(ex.getMessage(), HttpStatus.UNAUTHORIZED.value()));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiError> handlerForbiddenException(ForbiddenException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ApiError(ex.getMessage(), HttpStatus.FORBIDDEN.value()));
    }
}
