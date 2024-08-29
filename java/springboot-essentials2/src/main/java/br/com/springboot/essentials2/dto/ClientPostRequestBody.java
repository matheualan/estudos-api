package br.com.springboot.essentials2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientPostRequestBody {

    @NotBlank(message = "O campo name não pode ser nulo nem vazio.") //Aplicável apenas para String (Campo de texto)
    @Size(min = 3, max = 255, message = "O campo name deve conter entre 3 a 255 caracteres.")
    private String name;

    @Size(min = 8, max = 11, message = "Informe um número para contato válido.")
    private String phone;

}
