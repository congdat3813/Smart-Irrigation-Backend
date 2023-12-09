package com.hcmut.smartirrigation.exception;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ModelError> handleAllException(Exception ex, WebRequest webRequest) {
        return ResponseEntity.internalServerError().body(ModelError.builder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                        .message(ex.getMessage())
                .build());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ModelError> handlerRequestException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ModelError.builder()
                .code(HttpStatus.NOT_FOUND.toString())
                .message(ex.getMessage())
                .build());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ModelError> handlerRequestException(BadRequestException ex) {
        return ResponseEntity.badRequest().body(ModelError.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .build());
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ModelError> unauthorizedException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ModelError.builder()
                .code(HttpStatus.UNAUTHORIZED.toString())
                .message(ex.getMessage())
                .build());
    }

    @ExceptionHandler({ForbiddenException.class, AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ModelError> forbiddenException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ModelError.builder()
                .code(HttpStatus.FORBIDDEN.toString())
                .message(ex.getMessage())
                .build());
    }
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ModelError> badCredentialException(BadCredentialsException badCredentialsException) {
        return ResponseEntity.badRequest().body(ModelError.builder()
                        .code("4002")
                        .message("Username or password is wrong")
                .build());
    }
}