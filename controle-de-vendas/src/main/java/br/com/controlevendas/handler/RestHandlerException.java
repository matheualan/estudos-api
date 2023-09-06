package br.com.controlevendas.handler;

import br.com.controlevendas.dto.UserNotFoundExceptionDTO;
import br.com.controlevendas.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestHandlerException {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserNotFoundExceptionDTO> handlerUserNotFoundException(UserNotFoundException userNFExc) {
        return new ResponseEntity<UserNotFoundExceptionDTO>(UserNotFoundExceptionDTO.builder()
                .message("Erro ao executar a solicação. Verifique a documentação.")
                .messageDeveloper(userNFExc.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(userNFExc.getClass().getName())
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.BAD_REQUEST);
    }

}
