package com.salescontrol.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressPostDTO {

    @NotBlank(message = "O campo CEP deve conter 8 números")
    @Size(min = 8, max = 8, message = "O campo CEP deve conter 8 números")
    private String cep;

}
