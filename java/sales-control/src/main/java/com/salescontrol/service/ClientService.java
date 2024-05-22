package com.salescontrol.service;

import com.salescontrol.dto.client.ClientGetRequestBody;
import com.salescontrol.dto.client.ClientPostRequestBody;
import com.salescontrol.dto.client.ClientWithOrderPostDTO;
import com.salescontrol.exception.ClientNotFoundException;
import com.salescontrol.mapper.ClientMapper;
import com.salescontrol.model.Client;
import com.salescontrol.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<ClientPostRequestBody> saveMultipleClients(List<ClientPostRequestBody> multipleClients) {
        List<Client> clients = new ArrayList<>();
        for (ClientPostRequestBody clientPost : multipleClients) {
            clients.add(ClientMapper.INSTANCE.toClient(clientPost));
        }
        clientRepository.saveAll(clients);
        return multipleClients;
    }

    public List<Client> listClients() {
        return clientRepository.findAll();
    }

    public List<ClientGetRequestBody> listClientsDTO() {
        List<Client> clients = clientRepository.findAll();
        List<ClientGetRequestBody> clientsDTO = new ArrayList<>();
        for (Client client : clients) {
            clientsDTO.add(ClientMapper.INSTANCE.toClientGet(client));
        }
        return clientsDTO;
    }

    public ClientGetRequestBody findByName(String name) {
        Client client = clientRepository.findByName(name).orElseThrow(
                () -> new ClientNotFoundException("Cliente não encontrado."));
        return ClientMapper.INSTANCE.toClientGet(client);
    }

    public ClientGetRequestBody findByCpf(String cpf) {
        Client client = clientRepository.findByCpf(cpf).orElseThrow(
                () -> new ClientNotFoundException("Cliente não encontrado."));
        return ClientMapper.INSTANCE.toClientGet(client);
    }

    public ClientWithOrderPostDTO saveClientWithOrder(ClientWithOrderPostDTO clientWithOrderPostDTO) {
        Client client = ClientMapper.INSTANCE.toClient(clientWithOrderPostDTO);
        clientRepository.save(client);
        return clientWithOrderPostDTO;
    }

}
