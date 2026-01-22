package com.loupfituserservice.userservice.infrastructure.handler;

import com.loupfituserservice.userservice.business.record.ApiError;
import com.loupfituserservice.userservice.business.record.login.out.LoginResponse;
import com.loupfituserservice.userservice.infrastructure.exceptions.ConflictException;
import com.loupfituserservice.userservice.infrastructure.exceptions.ForbiddenException;
import com.loupfituserservice.userservice.infrastructure.exceptions.UnauthorizedException;
import com.loupfituserservice.userservice.infrastructure.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handlerResourceNotFoundException(
            ResourceNotFoundException ex
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError(
                        ex.getMessage(),
                        HttpStatus.NOT_FOUND.value()
                ));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiError> handlerConflictException(
            ConflictException ex
    ) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiError(
                        ex.getMessage(),
                        HttpStatus.CONFLICT.value()
                ));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiError> handlerUnauthorizedException(
            UnauthorizedException ex
    ) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        new ApiError(
                                ex.getMessage(),
                                HttpStatus.UNAUTHORIZED.value()
                        )
                );
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiError> handlerForbiddenException(
            ForbiddenException ex
    ) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiError(
                        ex.getMessage(),
                        HttpStatus.FORBIDDEN.value()
                ));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> handlerBadCredentials(
            BadCredentialsException ex
    ) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiError(
                        "Usuário ou senha inválidos",
                        HttpStatus.UNAUTHORIZED.value()
                ));
    }


}
