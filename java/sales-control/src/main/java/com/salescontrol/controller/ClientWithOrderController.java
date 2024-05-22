package com.salescontrol.controller;

import com.salescontrol.dto.client.ClientWithOrderPostDTO;
import com.salescontrol.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/client-with-order")
@RequiredArgsConstructor
@Log4j2
public class ClientWithOrderController {

    private final ClientService clientService;

    @PostMapping(value = "/saveClientWithOrder")
    public ResponseEntity<ClientWithOrderPostDTO> saveClientWithOrder(@RequestBody ClientWithOrderPostDTO client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClientWithOrder(client));
    }

}
