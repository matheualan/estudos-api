package br.com.controlevendas.dto;

import br.com.controlevendas.model.Address;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
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
