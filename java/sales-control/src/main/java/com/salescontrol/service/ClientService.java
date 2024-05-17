package com.salescontrol.service;

import com.salescontrol.dto.ClientPostRequestBody;
import com.salescontrol.mapper.ClientMapper;
import com.salescontrol.model.Client;
import com.salescontrol.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
