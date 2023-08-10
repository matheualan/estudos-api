package br.com.handler.exception.dto;

import br.com.handler.exception.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private String nome;

    private String cpf;

    public UsuarioDTO(Usuario usuario) {
        nome = usuario.getNome();
        cpf = usuario.getCpf();
    }

}
