package com.salescontrol.controller;

import com.salescontrol.dto.ClientPostRequestBody;
import com.salescontrol.model.Client;
import com.salescontrol.service.ClientService;
import com.salescontrol.util.DateUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
    public ResponseEntity<ClientPostRequestBody> saveClient(@RequestBody @Valid ClientPostRequestBody clientPost) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" POST saveClient()"));
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClient(clientPost));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Client>> listClients() {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" GET listClients()"));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.listClients());
    }

}
