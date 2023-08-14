package br.com.handler.exception.dto;

import br.com.handler.exception.entity.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    private UsuarioDTO usuarioDTO;

    public EnderecoDTO(Endereco endereco) {
        cep = endereco.getCep();
        logradouro = endereco.getLogradouro();
        complemento = endereco.getComplemento();
        bairro = endereco.getBairro();
        localidade = endereco.getLocalidade();
        uf = endereco.getUf();
    }

}
