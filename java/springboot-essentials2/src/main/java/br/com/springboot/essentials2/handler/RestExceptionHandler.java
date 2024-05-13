package br.com.springboot.essentials2.handler;

import br.com.springboot.essentials2.exception.BadRequestExceptionDetails;
import br.com.springboot.essentials2.exception.ClientNotFoundBadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice //Diz para todos os controllers que deve usar essa classe baseado em uma 'flag' definada por meio
//da anotação @ExceptionHandler
public class RestExceptionHandler {

    @ExceptionHandler(ClientNotFoundBadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerClientNotFoundBadRequestException(
            ClientNotFoundBadRequestException exc) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                BadRequestExceptionDetails.builder()
                        .title("Bad Request Exception Personalized - Check the Documentation")
                        .statusHttp(HttpStatus.BAD_REQUEST.value()) //.value() para pegar o valor int do status
                        .details(exc.getMessage())
                        .developerMessage(exc.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

}
