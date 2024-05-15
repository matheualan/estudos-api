package br.com.springboot.essentials2.controller.hardcode;

import br.com.springboot.essentials2.model.hardcode.Cliente;
import br.com.springboot.essentials2.service.hardcode.ClientServiceHardCode;
import br.com.springboot.essentials2.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
@Log4j2
public class ClientControllerWithServiceHardCode {

    private final DateUtil dateUtil;
    private final ClientServiceHardCode clientServiceHardCode;

    public ClientControllerWithServiceHardCode(DateUtil dateUtil, ClientServiceHardCode clientServiceHardCode) {
        this.dateUtil = dateUtil;
        this.clientServiceHardCode = clientServiceHardCode;
    }

    @PostMapping
    public ResponseEntity<Cliente> saveClient(@RequestBody Cliente client) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" Request: POST - Method: saveClient()"));
        return ResponseEntity.status(HttpStatus.CREATED).body(clientServiceHardCode.saveClient(client));
    }

    @GetMapping(value = "/list")
    public List<Cliente> listClients() {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" Request: GET - Method: listClients()"));
        return clientServiceHardCode.listAll();
    }

    @GetMapping(value = "/findClientByIndex/{index}")
    public ResponseEntity<Cliente> findClientByIndex(@PathVariable Integer index) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" Request: GET - Method: findClientByIndex()"));
        return ResponseEntity.status(HttpStatus.OK).body(clientServiceHardCode.findClientByIndex(index));
    }

    @GetMapping(value = "/findClientById/{id}")
    public ResponseEntity<Cliente> findClientById(@PathVariable Integer id) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" Request: GET - Method: findClientById()"));
        return ResponseEntity.status(HttpStatus.OK).body(clientServiceHardCode.findClientById(id));
    }

    @DeleteMapping(value = "/deleteClientById/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable Integer id) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" Request: DELETE - Method: deleteClientById()"));
        clientServiceHardCode.deleteClientById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(value = "/replaceClientById")
    public ResponseEntity<Void> replaceClient(@RequestBody Cliente client) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" Request: PUT - Method: replaceClientById()"));
        clientServiceHardCode.replaceClient(client);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
