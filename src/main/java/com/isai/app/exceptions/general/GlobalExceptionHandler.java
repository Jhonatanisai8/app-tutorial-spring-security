package com.isai.app.exceptions.general;

import com.isai.app.exceptions.personalization.ConflicException;
import com.isai.app.exceptions.personalization.JWTAuthenticationException;
import com.isai.app.exceptions.personalization.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorObject> handleNotFoundException(NotFoundException exception) {
        return new ResponseEntity<ErrorObject>(
                ErrorObject.builder()
                        .codigoEstado(HttpStatus.NOT_FOUND.value())
                        .mensaje(exception.getMessage())
                        .timestamp(new Date())
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ConflicException.class)
    public ResponseEntity<ErrorObject> handleConflitException(ConflicException exception) {
        return new ResponseEntity<ErrorObject>(
                ErrorObject.builder()
                        .codigoEstado(HttpStatus.CONFLICT.value())
                        .mensaje(exception.getMessage())
                        .timestamp(new Date())
                        .build(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(JWTAuthenticationException.class)
    public ResponseEntity<ErrorObject> handleAuthenticationCredentialsNotFoundException(JWTAuthenticationException exception) {
        return new ResponseEntity<ErrorObject>(
                ErrorObject.builder()
                        .codigoEstado(HttpStatus.UNAUTHORIZED.value())
                        .mensaje(exception.getMessage())
                        .timestamp(new Date())
                        .build(),
                HttpStatus.UNAUTHORIZED
        );
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handleInternalServer(Exception exception) {
        return new ResponseEntity<ErrorObject>(
                ErrorObject.builder()
                        .codigoEstado(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .mensaje(exception.getMessage())
                        .timestamp(new Date())
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
