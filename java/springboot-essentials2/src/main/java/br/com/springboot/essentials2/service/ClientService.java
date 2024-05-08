package br.com.springboot.essentials2.service;

import br.com.springboot.essentials2.dto.ClientGetFindById;
import br.com.springboot.essentials2.dto.ClientPostRequestBodyDTO;
import br.com.springboot.essentials2.dto.ClientPutRequestBody;
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

    public ClientPostRequestBodyDTO saveClient(ClientPostRequestBodyDTO clientDTO) {
//        var client = new Client(clientDTO);
        Client client = Client.builder()
                .name(clientDTO.getName())
                .phone(clientDTO.getPhone())
                .build();
        clientRepository.save(client);
        return clientDTO;
    }

    public void deleteClientById(Integer id) {
        clientRepository.delete(findClientById(id));
    }

    public void replaceClient(ClientPutRequestBody clientDTO) {
        Client foundClient = findClientById(clientDTO.getIdClientDTO());
        Client client = Client.builder()
                .idClient(foundClient.getIdClient())
                .name(clientDTO.getName())
                .phone(clientDTO.getPhone())
                .build();
        clientRepository.save(client);
    }

}
