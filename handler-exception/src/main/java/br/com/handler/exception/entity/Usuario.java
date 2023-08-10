package br.com.handler.exception.entity;

import br.com.handler.exception.dto.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nome;

    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataEntrada = LocalDateTime.now();

    public Usuario(UsuarioDTO usuarioDTO) {
        nome = usuarioDTO.getNome();
        cpf = usuarioDTO.getCpf();
    }

}
