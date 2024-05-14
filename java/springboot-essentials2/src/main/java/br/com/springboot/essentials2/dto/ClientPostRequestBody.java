package br.com.springboot.essentials2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientPostRequestBody {

    @NotBlank(message = "O campo name não pode ser nulo nem vazio.") //Aplicável apenas para String (Campo de texto)
    @Size(min = 3, max = 255, message = "O campo name deve conter entre 3 a 255 caracteres.")
    private String name;

    private String phone;

//    public ClientPostRequestBodyDTO(Client client) {
//        name = client.getName();
//    }

}
