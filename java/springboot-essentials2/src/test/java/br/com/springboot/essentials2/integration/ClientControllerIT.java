package br.com.springboot.essentials2.integration;

import br.com.springboot.essentials2.dto.ClientGetFindById;
import br.com.springboot.essentials2.model.Client;
import br.com.springboot.essentials2.repository.ClientRepository;
import br.com.springboot.essentials2.util.ClientCreator;
import br.com.springboot.essentials2.wrapper.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//Parametro define porta aleatoria para n startar na porta 8080 e rolar conflito com alguma app rodando na mesma porta
@AutoConfigureTestDatabase //Anotacao para executar os testes com banco de dados em memoria
class ClientControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate; //Essa propriedade vai localizar a porta inicializada pelo webEnvironment

    @LocalServerPort //Essa anotacao pega a porta que esta sendo utilizada no momento
    private int port;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName("pageClients returns list of clients inside page object when successful")
    void pageClients_ReturnsListOfClientsInsidePageObject_WhenSuccessful() {
        Client savedClient = clientRepository.save(ClientCreator.createClientToBeSaved());

        String expectedName = savedClient.getName();

//        @formatter:off
        PageableResponse<ClientGetFindById> clientPage = testRestTemplate.exchange("/client/page",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageableResponse<ClientGetFindById>>(){}).getBody();
//        @formatter:on

        Assertions.assertThat(clientPage).isNotNull();
        Assertions.assertThat(clientPage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(clientPage.toList().get(0).getName()).isEqualTo(expectedName);
    }



}
