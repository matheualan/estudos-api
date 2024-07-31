package br.com.springboot.essentials2.client;

import br.com.springboot.essentials2.dto.ClientGetFindById;
import br.com.springboot.essentials2.model.Client;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClientRestTemplate {

    public static void main(String[] args) {

        ResponseEntity<ClientGetFindById> forEntity = new RestTemplate()
                .getForEntity("http://localhost:8082/client/find-id/2", ClientGetFindById.class);
        log.info(forEntity);

        ClientGetFindById forObject = new RestTemplate()
                .getForObject("http://localhost:8082/client/find-id/2", ClientGetFindById.class);
        log.info(forObject);

        ClientGetFindById forObject2 = new RestTemplate()
                .getForObject("http://localhost:8082/client/find-id/{id}", ClientGetFindById.class, 2);
        log.info(forObject2);

        ClientGetFindById[] clients = new RestTemplate().getForObject("http://localhost:8082/client/list-all",
                ClientGetFindById[].class);
        log.info(Arrays.toString(clients));

//        @formatter:off
        ResponseEntity<List<ClientGetFindById>> exchange = new RestTemplate()
                .exchange("http://localhost:8082/client/list-all",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>(){});
        log.info(exchange.getBody());
//        @formatter:on

    }

}
