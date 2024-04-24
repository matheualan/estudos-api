package br.com.springboot.essentials2.controller;

import br.com.springboot.essentials2.model.Client;
import br.com.springboot.essentials2.service.ClientService;
import br.com.springboot.essentials2.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/client")
@Log4j2
public class ClientController {

    private final DateUtil dateUtil;
    private final ClientService clientService;

    public ClientController(DateUtil dateUtil, ClientService clientService) {
        this.dateUtil = dateUtil;
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> saveClient(@RequestBody Client client) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" Request: POST, Method: saveClient()"));
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClient(client));
    }

    @GetMapping(value = "/list")
    public List<Client> listClients() {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" Request: GET, Method: listClients()"));
        return clientService.listAll();
    }

    @GetMapping(value = "/findClientByIndex/{index}")
    public ResponseEntity<Client> findClientByIndex(@PathVariable Integer index) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" Request: GET, Method: findClientByIndex()"));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findClientByIndex(index));
    }

    @GetMapping(value = "/findClientById/{id}")
    public ResponseEntity<Client> findClientById(@PathVariable Integer id) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" Request: GET, Method: findClientById()"));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findClientById(id));
    }

    @DeleteMapping(value = "/deleteClientById/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable Integer id) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" Request: DELETE, Method: deleteClientById()"));
        clientService.deleteClientById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(value = "/replaceClientById")
    public ResponseEntity<Void> replaceClientById(@RequestBody Client client) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" Request: PUT, Method: replaceClientById()"));
        clientService.replaceClientById(client);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
