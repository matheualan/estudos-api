package br.com.handler.exception.handler;

import br.com.handler.exception.dto.UsuarioBadRequestExceptionDTO;
import br.com.handler.exception.exception.UsuarioBadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;

@ControllerAdvice
public class HandlerUsuarioBadRequestException {

    @ExceptionHandler(UsuarioBadRequestException.class)
    public ResponseEntity<UsuarioBadRequestExceptionDTO> handlerUsuarioBadRequestExcpt(UsuarioBadRequestException e) {
        return new ResponseEntity<UsuarioBadRequestExceptionDTO>(UsuarioBadRequestExceptionDTO.builder()
                .mensagem(e.getMessage().toUpperCase(Locale.ROOT))
                .erro(e.getClass().getName())
                .statusHttp(HttpStatus.BAD_REQUEST.value())
                .momentoExato(LocalDateTime.now())
                .build(), HttpStatus.BAD_REQUEST);
    }

}