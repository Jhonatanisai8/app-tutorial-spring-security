package com.isai.app.exceptions.general;

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
}
