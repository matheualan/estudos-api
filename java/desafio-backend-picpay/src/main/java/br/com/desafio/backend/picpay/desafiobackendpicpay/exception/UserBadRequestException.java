package br.com.desafio.backend.picpay.desafiobackendpicpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserBadRequestException extends RuntimeException {

    public UserBadRequestException(String message) {
        super(message);
    }

}