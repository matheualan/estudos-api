package br.com.handler.exception.dto;

import br.com.handler.exception.entity.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    @NotBlank(message = "O campo nome não pode ser nulo ou vazio")
    private String nome;

    @NotBlank(message = "O campo cpf não pode ser nulo ou vazio")
    @Size(min = 11, max = 14)
    private String cpf;

    public UsuarioDTO(Usuario usuario) {
        nome = usuario.getNome();
        cpf = usuario.getCpf();
    }

}
