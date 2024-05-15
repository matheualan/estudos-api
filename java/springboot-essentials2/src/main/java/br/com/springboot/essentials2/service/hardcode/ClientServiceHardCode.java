package br.com.springboot.essentials2.service.hardcode;

import br.com.springboot.essentials2.model.hardcode.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceHardCode {

    private static List<Cliente> listClients;

    static {
        listClients = new ArrayList<>(List.of(new Cliente(1, "Mattus"),
                new Cliente(2, "Alien"),
                new Cliente(3, "Mosh")));
    }

    public Cliente saveClient(Cliente client) {
        Cliente newClient = new Cliente(client.getId(), client.getName());
        listClients.add(newClient);
        return newClient;
    }

    public List<Cliente> listAll() {
        return listClients;
    }

    public Cliente findClientByIndex(Integer index) {
        return listClients.get(index - 1);
    }

    public Cliente findClientById(Integer id) {
        return listClients
                .stream()
                .filter(client -> client.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não encontrado"));
    }

    public void deleteClientById(Integer id) {
        listClients.remove(findClientById(id));
    }

    public void replaceClient(Cliente client) {
        deleteClientById(client.getId());
        listClients.add(client);
    }

}
