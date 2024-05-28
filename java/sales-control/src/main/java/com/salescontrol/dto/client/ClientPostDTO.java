package com.salescontrol.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
public class ClientPostDTO {

    @NotBlank(message = "O campo 'name' não pode ser branco ou nulo.")
    @Size(min = 3, max = 255, message = "O campo 'name' deve conter entre 3 a 255 caracteres.")
    private String name;

    @NotBlank(message = "O campo 'CPF' não pode ser branco ou nulo.")
    @Size(min = 11, max = 11, message = "O campo 'CPF' deve conter 11 caracteres.")
    private String cpf;

    private Double totalQuantity = 0.0;

    private BigDecimal totalPurchased = BigDecimal.ZERO;

}
