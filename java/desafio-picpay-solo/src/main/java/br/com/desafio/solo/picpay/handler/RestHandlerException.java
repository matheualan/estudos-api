package br.com.desafio.solo.picpay.handler;

import br.com.desafio.solo.picpay.dto.record.HandlerExceptionDTO;
import br.com.desafio.solo.picpay.exception.PersonalizedException;
import br.com.desafio.solo.picpay.exception.PersonalizedExceptionDTO;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Log4j2
public class RestHandlerException {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity handleException(Exception e) {
//        String message = "ERROR!";
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HandlerExceptionDTO> manipuladorDeException(Exception e) {
        log.info("Passando no método manipulador");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HandlerExceptionDTO.builder()
                .message(e.getMessage())
                .messageDeveloper(e.getClass().getName())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(PersonalizedException.class)
    public ResponseEntity<PersonalizedExceptionDTO> handlerPersonalizedException(PersonalizedException pe) {
        log.info("Processing handler personalized");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(PersonalizedExceptionDTO.builder()
                .messageDeveloper("Erro ao realizar a transação. Check the documentation")
                .messageError(pe.getMessage())
                .status(HttpStatus.UNAUTHORIZED.value())
                .error(pe.getClass().getName())
                .timestamp(LocalDateTime.now())
                .build());
    }

}
