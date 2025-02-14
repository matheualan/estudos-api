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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//Parametro define porta aleatoria para n startar na porta 8080 e rolar conflito com alguma app rodando na mesma porta
@AutoConfigureTestDatabase //Anotacao para executar os testes usando banco de dados em memoria
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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
//        Usar exchange() qdo for retornar List<T> pq o getForObject n suporta diretamente a deserializacao para List<T>
//        pois a conversao generica n eh feita automaticamente
        PageableResponse<ClientGetFindById> clientPage = testRestTemplate.exchange("/client/page",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageableResponse<ClientGetFindById>>(){}).getBody();
//        @formatter:on

        Assertions.assertThat(clientPage).isNotNull();
        Assertions.assertThat(clientPage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(clientPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("listClient returns list of clients when suceessful")
    void listClient_ReturnsListOfClients_WhenSuccessful() {
        Client savedClient = clientRepository.save(ClientCreator.createClientToBeSaved());
        String expectedName = savedClient.getName();

//        @formatter:off
        List<Client> clients = testRestTemplate.exchange("/client/list-all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Client>>(){}).getBody();
//        @formatter:on


        Assertions.assertThat(clients).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(clients.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findClientById returns client by id when successful")
    void findClientById_ReturnClientById_WhenSuccessful() {
        Client savedClient = clientRepository.save(ClientCreator.createClientToBeSaved());
        Integer expectedId = savedClient.getIdClient();

        ClientGetFindById clientGet = testRestTemplate.getForObject("/client/find-id/{id}",
                ClientGetFindById.class,
                expectedId);

        Assertions.assertThat(clientGet.getIdClient()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findClientByName returns a list of client by name when successful")
    void findClientByName_ReturnListOfClientByName_WhenSuccessful() {
        Client client = clientRepository.save(ClientCreator.createValidClient());
        String expectedName = client.getName();
        String url = String.format("/client/list-by-name?name=%s", expectedName); //feito assim pq a url precisa de parametro

//        @formatter:off
        List<Client> listClientsByName = testRestTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Client>>(){}).getBody();
//        @formatter:on

        Assertions.assertThat(listClientsByName).isNotNull();
        Assertions.assertThat(listClientsByName.get(0).getName()).isEqualTo(client.getName()).isNotNull();
        Assertions.assertThat(listClientsByName.get(0).getIdClient()).isEqualTo(client.getIdClient());
    }

    @Test
    @DisplayName("findClientByName returns a empty list when client by name is not found")
    void findClientByName_ReturnsEmptyList_WhenClientByNameIsNotFound() {
//        BDDMockito.when(clientRepository.findByName(ArgumentMatchers.anyString()))
//                .thenReturn(List.of());

//        BDDMockito.when(clientRepository.findByName(ArgumentMatchers.anyString()))
//                .thenReturn(Collections.emptyList());
//
//        List<Client> listQualquer = clientRepository.findByName("Name qualquer");
//
//        Assertions.assertThat(listQualquer).isNotNull().isEmpty();

//        @formatter:off
        List<Client> responseClient = testRestTemplate.exchange("/client/list-by-name?name=metabots",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Client>>(){}).getBody();
//      @formatter:on

        Assertions.assertThat(responseClient).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("saveClient returns client when successful")
    void saveClient_ReturnsClient_WhenSuccessful() {
//        Client validClient = ClientCreator.createValidClient();
        Client validClient = clientRepository.save(ClientCreator.createValidClient());

        ResponseEntity<Client> clientResponseEntity = testRestTemplate.postForEntity("/client/save",
                validClient,
                Client.class);

        Assertions.assertThat(clientResponseEntity.getBody()).isNotNull();
//        Assertions.assertThat(clientResponseEntity.getBody().getIdClient()).isNotNull();
        Assertions.assertThat(clientResponseEntity.getBody().getName()).isEqualTo(validClient.getName()).isNotEmpty();
        Assertions.assertThat(clientResponseEntity.getBody().getPhone()).isEqualTo(validClient.getPhone()).isNotEmpty();
        Assertions.assertThat(clientResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("replaceClient updates client when successful")
    void replaceClient_UpdatesClient_WhenSuccessful() {
        Client client = clientRepository.save(ClientCreator.createValidClient());

        client.setName("Nome Atualizado");

        ResponseEntity<Void> resultPostForObj = testRestTemplate.exchange("/client/replace",
                HttpMethod.PUT,
                new HttpEntity<>(client),
                Void.class);

        Assertions.assertThat(resultPostForObj).isNotNull();
        Assertions.assertThat(resultPostForObj.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("deleteClient delete client when successful")
    void deleteClient_RemovesClient_WhenSuccessful() {
        Client clientSaved = clientRepository.save(ClientCreator.createValidClient());

        Integer idClient = clientSaved.getIdClient();

        ResponseEntity<Void> respondeDeleteExchange = testRestTemplate.exchange("/client/delete/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                idClient);

        Assertions.assertThat(respondeDeleteExchange).isNotNull();
        Assertions.assertThat(respondeDeleteExchange.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


}
