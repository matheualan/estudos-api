package br.com.springboot.essentials2.dto;

import lombok.Data;

@Data
public class ClientPutRequestBody {

    private Integer idClientDTO;

    private String name;

    private String phone;

}
