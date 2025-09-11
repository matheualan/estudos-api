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
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/client")
@RequiredArgsConstructor
@Log4j2
public class ClientController {

    private final ClientService clientService;
    private final DateUtil dateUtil;

    @PostMapping(path = "/save")
    public ResponseEntity<ClientPostRequestBody> saveClient(@RequestBody @Valid ClientPostRequestBody clientDTO) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" POST /client/save"));
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClient(clientDTO));
    }

    @PostMapping(path = "/saveAll")
    public ResponseEntity<List<ClientPostRequestBody>> saveAll(@RequestBody @Valid List<ClientPostRequestBody> clients) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" POST /client/saveAll"));
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveAll(clients));
    }

    @GetMapping(path = "/list-all")
    public ResponseEntity<List<Client>> listClient() {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" GET /client/list-all"));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.listAll());
    }

    @GetMapping(path = "/page") //sort = "name", direction = Sort.Direction.ASC
    public ResponseEntity<Page<ClientGetFindById>> pageClients(@PageableDefault(page = 0, size = 5,
                                                                direction = Sort.Direction.ASC) Pageable pageable) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" GET /client/page"));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.pageClientsMap(pageable));
    }

    @GetMapping(path = "/list-by-name") // Usar ? na URL para passar a var. Ex.: ?name=Matios / ?id=1&name=Alen
    public ResponseEntity<List<Client>> findClientByName(@RequestParam String name) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" GET /client/list-by-name"));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findByName(name));
    }

    @GetMapping(path = "/find-id/{id}")
    public ResponseEntity<ClientGetFindById> findClientById(@PathVariable Integer id) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" GET /client/find-id/{id}"));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findClient(id));
    }

    @GetMapping(path = "/find-entity/{id}")
    public ResponseEntity<Client> findClientEntityById(@PathVariable Integer id) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" GET /client/find-entity/{id}"));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findClientById(id));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" DELETE /client/delete/{id}"));
        clientService.deleteClientById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(path = "/replace")
    public ResponseEntity<Void> replaceClient(@RequestBody ClientPutRequestBody clientDTO) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" PUT /client/replace"));
        clientService.replaceClient(clientDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
