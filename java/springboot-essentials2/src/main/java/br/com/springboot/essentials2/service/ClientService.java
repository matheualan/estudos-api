package br.com.springboot.essentials2.service;

import br.com.springboot.essentials2.model.Client;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClientService {

    private final List<Client> listClients = List.of(new Client(1, "Mattus"),
            new Client(2, "Alien"),
            new Client(3, "Mosh"));

    public List<Client> listAll() {
        return listClients;
    }

    public Client findClientByIndex(Integer index) {
        return listClients.get(index - 1);
    }

    public Client findClientById(Integer id) {
        return listClients
                .stream()
                .filter(client -> client.getIdClient().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não encontrado"));
    }

}
