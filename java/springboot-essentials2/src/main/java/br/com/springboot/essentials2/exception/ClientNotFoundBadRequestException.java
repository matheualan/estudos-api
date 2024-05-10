package br.com.springboot.essentials2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ClientNotFoundBadRequestException extends RuntimeException {

    public ClientNotFoundBadRequestException(String message) {
        super(message);
    }

}
