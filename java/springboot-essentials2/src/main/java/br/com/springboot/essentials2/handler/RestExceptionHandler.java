package br.com.springboot.essentials2.handler;

import br.com.springboot.essentials2.exception.ClientNotFoundException;
import br.com.springboot.essentials2.exception.ClientNotFoundExceptionDetails;
import br.com.springboot.essentials2.exception.ExceptionDetails;
import br.com.springboot.essentials2.exception.ValidationExceptionDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

//@RestControllerAdvice
@ControllerAdvice //Diz para todos os controllers que deve usar essa classe baseado em uma 'flag' definada por meio
//da anotação @ExceptionHandler
@Log4j2
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ClientNotFoundExceptionDetails> handleClientNotFoundException(
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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        log.info("Fields {}", exception.getBindingResult().getFieldError().getField());

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ValidationExceptionDetails.builder()
                        .title("Method Argument Not Valid Exception Personalized - Invalid Fields")
                        .statusHttp(HttpStatus.BAD_REQUEST.value()) //.value() para pegar o valor int do status
                        .details(exception.getMessage())
                        .developerMessage(exception.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .fields(fields)
                        .fieldsMessage(fieldsMessage)
                        .build()
        );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception exception, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title(exception.getCause().getMessage())
                .statusHttp(statusCode.value()) //.value() para pegar o valor int do status
                .details(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .timestamp(LocalDateTime.now())
                .build();

        return createResponseEntity(exceptionDetails, headers, statusCode, request);
    }

}
