package br.com.springboot.essentials2.dto;

import lombok.Data;

@Data
public class ClientPostRequestBodyDTO {

    private String name;

    private String phone;

//    public ClientPostRequestBodyDTO(Client client) {
//        name = client.getName();
//    }

}
