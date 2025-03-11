package br.com.springboot.essentials2.service;

import br.com.springboot.essentials2.dto.ClientGetFindById;
import br.com.springboot.essentials2.dto.ClientPostRequestBody;
import br.com.springboot.essentials2.dto.ClientPutRequestBody;
import br.com.springboot.essentials2.exception.ClientNotFoundException;
import br.com.springboot.essentials2.mapper.ClientMapper;
import br.com.springboot.essentials2.model.Client;
import br.com.springboot.essentials2.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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
            ClientGetFindById clientGetFindById = ClientMapper.INSTANCE.toClientGet(client);
            listClientGet.add(clientGetFindById);
        }

        Page<ClientGetFindById> pageClientGet = new PageImpl<>(listClientGet);

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
        return ClientMapper.INSTANCE.toClientGet(clientById);
    }

    @Transactional //(rollbackFor = Exception.class)
    public ClientPostRequestBody saveClient(ClientPostRequestBody clientPostRequestBody) {

//        Client client = Client.builder()
//                .name(clientPostRequestBodyDTO.getName())
//                .phone(clientPostRequestBodyDTO.getPhone())
//                .build();

        Client client = ClientMapper.INSTANCE.toClient(clientPostRequestBody);
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
        Client foundClient = findClientById(clientPutRequestBody.getIdClient());

        Client client = ClientMapper.INSTANCE.toClient(clientPutRequestBody);

        BeanUtils.copyProperties(client, foundClient);

        clientRepository.save(foundClient);
    }

}
