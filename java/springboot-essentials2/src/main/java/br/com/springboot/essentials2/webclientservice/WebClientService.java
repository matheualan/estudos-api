package br.com.springboot.essentials2.webclientservice;

import br.com.springboot.essentials2.dto.ClientGetFindById;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebClientService {

    private final WebClient webClient;

    public WebClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<ClientGetFindById> getClientById(Integer id) {
        return webClient.get()
                .uri("/get/{id}")
                .retrieve()
                .bodyToMono(ClientGetFindById.class);
    }

}
