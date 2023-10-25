package br.com.handler.exception.dto;

import br.com.handler.exception.entity.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"usuarioDTO", "teste"})
public class EnderecoDTO {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    private UsuarioDTO usuarioDTO;
    private String teste;

    public EnderecoDTO(Endereco endereco) {
        cep = endereco.getCep();
        logradouro = endereco.getLogradouro();
        complemento = endereco.getComplemento();
        bairro = endereco.getBairro();
        localidade = endereco.getLocalidade();
        uf = endereco.getUf();
    }

}
