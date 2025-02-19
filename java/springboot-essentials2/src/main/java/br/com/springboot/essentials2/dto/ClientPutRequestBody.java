package br.com.springboot.essentials2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientPutRequestBody {

    private Integer idClient;

    private String name;

    private String phone;

}
