package br.com.desafio.solo.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class PersonalizedException extends RuntimeException {

    public PersonalizedException(String message) {
        super(message);
    }

}
