package com.salescontrol.service;

import com.salescontrol.dto.ClientGetRequestBody;
import com.salescontrol.dto.ClientPostRequestBody;
import com.salescontrol.mapper.ClientMapper;
import com.salescontrol.model.Client;
import com.salescontrol.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientPostRequestBody saveClient(ClientPostRequestBody clientPostRequestBody) {
        Client client = ClientMapper.INSTANCE.toClient(clientPostRequestBody);
        clientRepository.save(client);
        return clientPostRequestBody;
    }

    public List<Client> listClients() {
        return clientRepository.findAll();
    }

    public ClientGetRequestBody findByName(String name) {
        Client client = clientRepository.findByName(name).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não encontrado."));
        return ClientMapper.INSTANCE.toClientGet(client);
    }

    public ClientGetRequestBody findByCpf(String cpf) {
        Client client = clientRepository.findByCpf(cpf).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não encontrado.")
        );
        return ClientMapper.INSTANCE.toClientGet(client);
    }

}
