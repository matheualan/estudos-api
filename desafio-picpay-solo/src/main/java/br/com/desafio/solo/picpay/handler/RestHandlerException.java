package br.com.desafio.solo.picpay.handler;

import br.com.desafio.solo.picpay.dto.record.HandlerExceptionDTO;
import lombok.extern.log4j.Log4j2;
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

}
