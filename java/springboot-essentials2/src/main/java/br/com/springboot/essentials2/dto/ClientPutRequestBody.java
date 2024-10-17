package br.com.springboot.essentials2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientPutRequestBody {

    private Integer idClient;

    private String name;

    private String phone;

}
