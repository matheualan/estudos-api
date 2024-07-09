package br.com.springboot.essentials2.service;

import br.com.springboot.essentials2.dto.ClientGetFindById;
import br.com.springboot.essentials2.dto.ClientPostRequestBody;
import br.com.springboot.essentials2.dto.ClientPutRequestBody;
import br.com.springboot.essentials2.exception.ClientNotFoundException;
import br.com.springboot.essentials2.mapper.ClientMapper;
import br.com.springboot.essentials2.model.Client;
import br.com.springboot.essentials2.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> listAll() {
        return clientRepository.findAll();
    }

    public Page<ClientGetFindById> pageClients(Pageable pageable) {
        Page<Client> pageClients = clientRepository.findAll(pageable);

        List<ClientGetFindById> listClientGet = new ArrayList<>();

        for (Client client : pageClients) {
            Optional<ClientGetFindById> clientGetFindById = ClientMapper.INSTANCE.toClientGet(client);
//            var clientGetFindById = new ClientGetFindById(client);
            listClientGet.add(clientGetFindById.get());
        }

        Page<ClientGetFindById> pageClientGet = new PageImpl<ClientGetFindById>(listClientGet);

        return pageClientGet;
    }

    public List<Client> findByName(String name) {
        return clientRepository.findByName(name);
    }

    public Client findClientById(Integer id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client não encontrado"));
    }

    public ClientGetFindById findClient(Integer id) {
        Client clientById = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Cliente não encontrado"));
        return new ClientGetFindById(clientById);
    }

    @Transactional //(rollbackFor = Exception.class)
    public ClientPostRequestBody saveClient(ClientPostRequestBody clientPostRequestBody) {
//        var client = new Client(clientPostRequestBodyDTO);

//        Client client = Client.builder()
//                .name(clientPostRequestBodyDTO.getName())
//                .phone(clientPostRequestBodyDTO.getPhone())
//                .build();

        Client client = ClientMapper.INSTANCE.toClient(clientPostRequestBody); //map struct
//        if (client.getName() == null || client.getName().isEmpty()) {
//            throw new RuntimeException("O campo nome deve conter no mínimo 3 caracteres.");
//        }
        clientRepository.save(client);
        return clientPostRequestBody;
    }

    public List<ClientPostRequestBody> saveAll(List<ClientPostRequestBody> clients) {
        List<Client> listClients = new ArrayList<>();
        for (ClientPostRequestBody clientDTO : clients) {
            Client client = ClientMapper.INSTANCE.toClient(clientDTO);
            listClients.add(client);
        }
        clientRepository.saveAll(listClients);
        return clients;
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
