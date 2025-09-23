package br.com.securitystudy.handler;

import br.com.securitystudy.exception.NullException;
import br.com.securitystudy.exception.details.NullPointerExceptionDetails;
import br.com.securitystudy.exception.details.InternalAuthenticationServiceExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<NullPointerExceptionDetails> handlerNullPointerException(NullPointerException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(NullPointerExceptionDetails.builder()
                        .title("Null Pointer Exception - Check the documention.")
                        .httpStatus(HttpStatus.BAD_REQUEST.value())
                        .details(exception.getMessage())
                        .developerMessage(exception.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<InternalAuthenticationServiceExceptionDetails> handlerUserDetailsServiceReturnNullException
            (InternalAuthenticationServiceException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(InternalAuthenticationServiceExceptionDetails.builder()
                .title("User Details Service Null Exception")
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .details(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .timestamp(LocalDateTime.now())
                .build());
    }

}