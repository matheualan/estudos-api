package br.com.springboot.essentials2.service;

import br.com.springboot.essentials2.dto.ClientGetFindById;
import br.com.springboot.essentials2.dto.ClientPostRequestBody;
import br.com.springboot.essentials2.dto.ClientPutRequestBody;
import br.com.springboot.essentials2.mapper.ClientMapper;
import br.com.springboot.essentials2.model.Client;
import br.com.springboot.essentials2.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> listAll() {
        return clientRepository.findAll();
    }

    public Client findClientById(Integer id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client não encontrado"));
    }

    public ClientGetFindById findClient(Integer id) {
        Client client = findClientById(id);
        return new ClientGetFindById(client);
    }

    public ClientPostRequestBody saveClient(ClientPostRequestBody clientPostRequestBody) {
//        var client = new Client(clientPostRequestBodyDTO);

//        Client client = Client.builder()
//                .name(clientPostRequestBodyDTO.getName())
//                .phone(clientPostRequestBodyDTO.getPhone())
//                .build();

        Client client = ClientMapper.INSTANCE.toClient(clientPostRequestBody);
        clientRepository.save(client);
        return clientPostRequestBody;
    }

    public void deleteClientById(Integer id) {
        clientRepository.delete(findClientById(id));
    }

    public void replaceClient(ClientPutRequestBody clientPutRequestBody) {
        Client foundClient = findClientById(clientPutRequestBody.getIdClientDTO());

//        Client client = Client.builder()
//                .idClient(foundClient.getIdClient())
//                .name(clientPutRequestBody.getName())
//                .phone(clientPutRequestBody.getPhone())
//                .build();

        Client client = ClientMapper.INSTANCE.toClient(clientPutRequestBody);
        client.setIdClient(foundClient.getIdClient());
        clientRepository.save(client);
    }

}
