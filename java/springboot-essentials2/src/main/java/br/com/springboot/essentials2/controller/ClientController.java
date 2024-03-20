package br.com.springboot.essentials2.controller;

import br.com.springboot.essentials2.model.Client;
import br.com.springboot.essentials2.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/client") //localhost:8080/client/list
@Log4j2
public class ClientController {

    private final DateUtil dateUtil;

    public ClientController(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }

    @GetMapping(value = "/list")
    public List<Client> listClients() {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" Request: GET, method: listClients()"));
        return List.of(new Client("Mattos"), new Client("Jackson"));
    }

}
