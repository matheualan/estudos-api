package br.com.securitystudy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserDetailsServiceReturnNullException extends InternalAuthenticationServiceException {

    public UserDetailsServiceReturnNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDetailsServiceReturnNullException(String message) {
        super(message);
    }

}