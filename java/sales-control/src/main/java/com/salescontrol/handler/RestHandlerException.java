package com.salescontrol.handler;

import com.salescontrol.exception.ClientNotFoundException;
import com.salescontrol.exception.ClientNotFoundExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestHandlerException {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ClientNotFoundExceptionDetails> handlerClientNotFoundException(ClientNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClientNotFoundExceptionDetails.builder()
                .error("BAD REQUEST")
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .developMessage(e.getClass().getName())
                .timestamp(LocalDateTime.now())
                .build());
    }

}
