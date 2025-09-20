package br.com.securitystudy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FailedCreationTokenJWTEXception extends RuntimeException {

    public FailedCreationTokenJWTEXception(String message) {
        super(message);
    }

    public FailedCreationTokenJWTEXception(String message, Throwable cause) {
        super(message, cause);
    }
}