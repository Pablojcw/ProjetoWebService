package com.app.exeption.exeption;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResorceExeptionHandler {

    @ExceptionHandler(ObjectNotFoundExeption.class)
    public ResponseEntity<StanderError> ObjectNotFound(ObjectNotFoundExeption e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StanderError error = new StanderError(System.currentTimeMillis(),
                status.value(), "Não encontrado",
                e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }


}
