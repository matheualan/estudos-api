package br.com.handler.exception.service;

import br.com.handler.exception.dto.CepEnderecoDTO;
import br.com.handler.exception.dto.UsuarioDTO;
import br.com.handler.exception.entity.Usuario;
import br.com.handler.exception.exception.UsuarioBadRequestException;
import br.com.handler.exception.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final WebClient webClient;

    public UsuarioService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<CepEnderecoDTO> getEnderecoByCep(String cep) {
        return webClient.get()
                .uri("https://viacep.com.br/ws/" + cep + "/json/")
                .retrieve()
                .bodyToMono(CepEnderecoDTO.class);
    }

    public Mono<UsuarioDTO> preencherUsuarioComEndereco(UsuarioDTO usuarioDTO, String cep) {
        Mono<CepEnderecoDTO> enderecoByCep = getEnderecoByCep(cep);
        Usuario usuario = new Usuario(usuarioDTO);
        usuarioRepository.save(usuario);
        return null;
    }

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
