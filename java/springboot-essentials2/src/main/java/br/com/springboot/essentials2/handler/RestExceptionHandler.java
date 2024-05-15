package br.com.springboot.essentials2.handler;

import br.com.springboot.essentials2.exception.ClientNotFoundExceptionDetails;
import br.com.springboot.essentials2.exception.ClientNotFoundException;
import br.com.springboot.essentials2.exception.ValidationExceptionDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice //Diz para todos os controllers que deve usar essa classe baseado em uma 'flag' definada por meio
//da anotação @ExceptionHandler
@Log4j2
public class RestExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ClientNotFoundExceptionDetails> handlerClientNotFoundException(
            ClientNotFoundException exception) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ClientNotFoundExceptionDetails.builder()
                        .title("Bad Request Exception Personalized - Check the Documentation")
                        .statusHttp(HttpStatus.BAD_REQUEST.value()) //.value() para pegar o valor int do status
                        .details(exception.getMessage())
                        .developerMessage(exception.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
//        log.info("Fields {}", exception.getBindingResult().getFieldError().getField());

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ValidationExceptionDetails.builder()
                        .title("Method Argument Not Valid Exception Personalized - Check the Documentation")
                        .statusHttp(HttpStatus.BAD_REQUEST.value()) //.value() para pegar o valor int do status
                        .details(exception.getMessage())
                        .developerMessage(exception.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .fields("")
                        .fieldsMessage("")
                        .build()
        );
    }

}
