package br.com.handler.exception.entity;

import br.com.handler.exception.dto.EnderecoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_enderecos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEndereco;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", referencedColumnName = "idUsuario")
    private Usuario usuario;

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    public Endereco(EnderecoDTO enderecoDTO) {
        cep = enderecoDTO.getCep();
        logradouro = enderecoDTO.getLogradouro();
        complemento = enderecoDTO.getComplemento();
        bairro = enderecoDTO.getBairro();
        localidade = enderecoDTO.getLocalidade();
        uf = enderecoDTO.getUf();
    }

}
