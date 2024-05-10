package br.com.springboot.essentials2.service;

import br.com.springboot.essentials2.dto.ClientGetFindById;
import br.com.springboot.essentials2.dto.ClientPostRequestBody;
import br.com.springboot.essentials2.dto.ClientPutRequestBody;
import br.com.springboot.essentials2.exception.ClientNotFoundBadRequestException;
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

    public List<Client> findByName(String name) {
        return clientRepository.findByName(name);
    }

    public Client findClientById(Integer id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client não encontrado"));
    }

    public ClientGetFindById findClient(Integer id) {
//        Client client = findClientById(id);
        Client clientById = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundBadRequestException("Cliente não encontrado"));
        return new ClientGetFindById(clientById);
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

//        CRIANDO OBJETO USANDO @BUILDER DO LOMBOK - ANOTAÇÃO @BUILDER ESTÁ NA ENTIDADE
//        Client client = Client.builder()
//                .idClient(foundClient.getIdClient())
//                .name(clientPutRequestBody.getName())
//                .phone(clientPutRequestBody.getPhone())
//                .build();

        Client client = ClientMapper.INSTANCE.toClient(clientPutRequestBody);
        clientRepository.save(client);
    }

}
