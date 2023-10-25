package br.com.desafio.solo.picpay.dto.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class HandlerExceptionDTO {

    private String message;
    private int status;
    private String messageDeveloper;
    @JsonFormat(pattern = "HH:mm:ss dd/MM/yyyy")
    private LocalDateTime timestamp;

}
