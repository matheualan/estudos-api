package br.com.controlevendas.dto;

import br.com.controlevendas.model.Address;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"userDTO"})
public class AddressDTO {

    @Size(min = 8, max = 8, message = "O campo CEP deve conter 8 caracteres")
    private String cep;

    @Size(min = 3, max = 50, message = "O campo LOGRADOURO deve conter entre 3 a 50 caracteres")
    private String logradouro;

    private String complemento;

    @Size(min = 3, max = 50, message = "O campo BAIRRO deve conter entre 3 a 50 caracteres")
    private String bairro;

    @Size(min = 3, max = 50, message = "O campo LOCALIDADE deve conter entre 3 a 50 caracteres")
    private String localidade;

    @Size(min = 2, max = 2, message = "O campo UF deve conter 2 caracteres")
    private String uf;

    private UserDTO userDTO;

    public AddressDTO(Address address) {
        cep = address.getCep();
        logradouro = address.getLogradouro();
        complemento = address.getComplemento();
        bairro = address.getBairro();
        localidade = address.getLocalidade();
        uf = address.getUf();
//        userDTO = new UserDTO(address.getUser());
    }

}
