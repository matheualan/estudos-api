package br.com.springboot.essentials2.controller;

import br.com.springboot.essentials2.dto.ClientGetFindById;
import br.com.springboot.essentials2.dto.ClientPostRequestBody;
import br.com.springboot.essentials2.dto.ClientPutRequestBody;
import br.com.springboot.essentials2.model.Client;
import br.com.springboot.essentials2.service.ClientService;
import br.com.springboot.essentials2.util.DateUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/client")
@RequiredArgsConstructor
@Log4j2
public class ClientController {

    private final ClientService clientService;
    private final DateUtil dateUtil;

    @PostMapping(value = "/save")
    public ResponseEntity<ClientPostRequestBody> saveClient(@RequestBody @Valid ClientPostRequestBody clientDTO) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" Request: POST, Method: saveClient()"));
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClient(clientDTO));
    }

    @PostMapping(value = "/saveAll")
    public ResponseEntity<List<ClientPostRequestBody>> saveAll(@RequestBody @Valid List<ClientPostRequestBody> clients) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" /POST saveAll()"));
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveAll(clients));
    }

    @GetMapping(value = "/find-all")
    public ResponseEntity<List<Client>> listClient() {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" Request: GET, Method: listClient()"));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.listAll());
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClientGetFindById>> pageClients(Pageable pageable) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" /GET pageClients()"));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.pageClients(pageable));
    }

    @GetMapping(value = "/find-name") // Usar ? na URL para passar a var. Ex.: ?name=Matios / ?id=1&name=Alen
    public ResponseEntity<List<Client>> findClientByName(@RequestParam String name) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" Request: GET, Method: findClientByName()"));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findByName(name));
    }

    @GetMapping(value = "/find-id/{id}")
    public ResponseEntity<ClientGetFindById> findClientById(@PathVariable Integer id) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" Request: GET, Method: findClientById()"));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findClient(id));
    }

    @GetMapping(value = "/find-entity/{id}")
    public ResponseEntity<Client> findClientEntityById(@PathVariable Integer id) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" Request: GET, Method: findClientById()"));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findClientById(id));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" Request: DELETE, Method: deleteClient()"));
        clientService.deleteClientById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(value = "/replace")
    public ResponseEntity<Void> replaceClient(@RequestBody ClientPutRequestBody clientDTO) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" Request: PUT, Method: replaceClient()"));
        clientService.replaceClient(clientDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
