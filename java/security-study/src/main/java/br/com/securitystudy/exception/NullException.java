package br.com.securitystudy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NullException extends NullPointerException {

    public NullException(String s) {
        super(s);
    }

}