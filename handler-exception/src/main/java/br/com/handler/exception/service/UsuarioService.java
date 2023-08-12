package br.com.handler.exception.service;

import br.com.handler.exception.dto.UsuarioDTO;
import br.com.handler.exception.entity.Usuario;
import br.com.handler.exception.exception.UsuarioBadRequestException;
import br.com.handler.exception.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO) {
        var usuario = new Usuario(usuarioDTO);
        if (usuario != null) {
            usuarioRepository.save(usuario);
        }
        return usuarioDTO;
    }

    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> all = usuarioRepository.findAll();
        List<UsuarioDTO> listUsuarioDTO = new ArrayList<UsuarioDTO>();
        for (Usuario u : all) {
            var usuarioDTO = new UsuarioDTO(u);
            listUsuarioDTO.add(usuarioDTO);
        }
        return listUsuarioDTO;
    }

    public UsuarioDTO buscarUsuarioPorCpf(String cpf) {
        return verificaSeExistePorCpf(cpf);
    }

    public UsuarioDTO verificaSeExistePorCpf(String cpf) {
        return new UsuarioDTO(usuarioRepository.findByCpf(cpf).orElseThrow(
                () -> new UsuarioBadRequestException("Usuário não encontrado")
        ));
    }

}
