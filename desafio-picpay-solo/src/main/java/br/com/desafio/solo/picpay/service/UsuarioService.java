package br.com.desafio.solo.picpay.service;

import br.com.desafio.solo.picpay.domain.user.Usuario;
import br.com.desafio.solo.picpay.dto.record.UsuarioDTO;
import br.com.desafio.solo.picpay.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {
        var usuario = new Usuario(usuarioDTO);
        usuarioRepository.save(usuario);
        return usuarioDTO;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

}