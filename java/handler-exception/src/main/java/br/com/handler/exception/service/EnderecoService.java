package br.com.handler.exception.service;

import br.com.handler.exception.dto.EnderecoDTO;
import br.com.handler.exception.entity.Endereco;
import br.com.handler.exception.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<EnderecoDTO> listarEnderecos() {
        List<Endereco> all = enderecoRepository.findAll();
        List<EnderecoDTO> listDTO = new ArrayList<>();
        for (Endereco e : all) {
            var dto = new EnderecoDTO(e);
            listDTO.add(dto);
        }
        return listDTO;
    }

}
