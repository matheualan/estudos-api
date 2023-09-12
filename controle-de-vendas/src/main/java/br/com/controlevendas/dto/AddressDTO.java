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

    @Size(min = 8, max = 8)
    private String cep;

    @Size(min = 3, max = 50)
    private String logradouro;

    private String complemento;

    @Size(min = 3, max = 50)
    private String bairro;

    @Size(min = 3, max = 50)
    private String localidade;

    @Size(min = 2, max = 2)
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
