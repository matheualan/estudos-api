package br.com.springboot.essentials2.client;

import br.com.springboot.essentials2.dto.ClientGetFindById;
import br.com.springboot.essentials2.dto.ClientPostRequestBody;
import br.com.springboot.essentials2.model.Client;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
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
                        new ParameterizedTypeReference<List<ClientGetFindById>>(){});
        log.info(exchange.getBody());
//        @formatter:on

        Client clientTest = Client.builder()
                .name("Cliente Teste")
                .phone("81999991111")
                .build();

        ClientPostRequestBody clientPostRequestBody = new RestTemplate()
                .postForObject("http://localhost:8082/client/save", clientTest, ClientPostRequestBody.class);
//        assert clientPostRequestBody != null;
        log.info("Saved anime: {}", clientPostRequestBody);

        Client clienteTeste = Client.builder()
                .name("Clientildo Chapolitano")
                .phone("81999992222")
                .build();

        ResponseEntity<ClientPostRequestBody> postExchange = new RestTemplate().exchange("http://localhost:8082/client/save",
                HttpMethod.POST,
                new HttpEntity<>(clienteTeste, headersPersonalized()),
                ClientPostRequestBody.class);
        log.info("Saved client: {}", postExchange);

    }

    private static HttpHeaders headersPersonalized() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

}
