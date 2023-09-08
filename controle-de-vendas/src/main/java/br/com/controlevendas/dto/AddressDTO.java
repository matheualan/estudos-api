package br.com.controlevendas.dto;

import br.com.controlevendas.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    public AddressDTO(Address address) {
        cep = address.getCep();
        logradouro = address.getLogradouro();
        complemento = address.getComplemento();
        bairro = address.getBairro();
        localidade = address.getLocalidade();
        uf = address.getUf();
    }

}
