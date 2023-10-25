package br.com.desafio.backend.picpay.desafiobackendpicpay.infra;

import br.com.desafio.backend.picpay.desafiobackendpicpay.dto.record.ExceptionHandlerDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestHandlerExceptions {

//    Exceção para quando tentar cadastrar novo usuário com documento já existente na base de dados
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionHandlerDTO> threatDuplicateEntry(DataIntegrityViolationException exc) {
//        return ResponseEntity.badRequest().build();
        ExceptionHandlerDTO exceptionHandlerDTO = new ExceptionHandlerDTO("Documento já cadastrado",
                HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionHandlerDTO);
    }

//    Exceção para usuário não encontrado
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionHandlerDTO> threatNotFound(EntityNotFoundException exc) {
        ExceptionHandlerDTO exceptionHandlerDTO = new ExceptionHandlerDTO("Usuário não encontrado",
                HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionHandlerDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionHandlerDTO> threatGeneralExceptions(Exception exc) {
        ExceptionHandlerDTO exceptionHandlerDTO = new ExceptionHandlerDTO(exc.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionHandlerDTO);
    }

}
