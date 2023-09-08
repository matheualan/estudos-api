package br.com.controlevendas.model;

import br.com.controlevendas.dto.AddressDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAddress;

    @Column(nullable = false, length = 9)
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "idUser")
    private User user;

    public Address(AddressDTO addressDTO) {
        cep = addressDTO.getCep();
        logradouro = addressDTO.getLogradouro();
        complemento = addressDTO.getComplemento();
        bairro = addressDTO.getBairro();
        localidade = addressDTO.getLocalidade();
        uf = addressDTO.getUf();
    }

}
