package br.com.handler.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsuarioBadRequestException extends RuntimeException {

    public UsuarioBadRequestException(String message) {
        super(message);
    }

}
