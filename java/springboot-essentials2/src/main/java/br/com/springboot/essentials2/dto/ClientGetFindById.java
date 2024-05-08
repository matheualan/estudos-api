package br.com.springboot.essentials2.dto;

import br.com.springboot.essentials2.model.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClientGetFindById {

    private String name;

    private String phone;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    public ClientGetFindById(Client client) {
        name = client.getName();
        createdAt = client.getCreatedAt();
    }

}
