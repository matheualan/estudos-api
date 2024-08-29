package br.com.springboot.essentials2.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientPutRequestBody {

    private Integer idClientDTO;

    private String name;

    private String phone;

}
