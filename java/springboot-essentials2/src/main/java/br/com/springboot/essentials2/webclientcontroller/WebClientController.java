package br.com.springboot.essentials2.webclientcontroller;

import br.com.springboot.essentials2.dto.ClientGetFindById;
import br.com.springboot.essentials2.webclientservice.WebClientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/webclient")
@Log4j2
public class WebClientController {

    private final WebClientService webClientService;

    public WebClientController(WebClientService webClientService) {
        this.webClientService = webClientService;
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<Mono<ClientGetFindById>> getClientByIdWebClient(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(webClientService.getClientById(id));
    }

}
