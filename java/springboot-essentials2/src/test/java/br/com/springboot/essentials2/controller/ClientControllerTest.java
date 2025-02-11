package br.com.springboot.essentials2.controller;

import br.com.springboot.essentials2.dto.ClientGetFindById;
import br.com.springboot.essentials2.dto.ClientPostRequestBody;
import br.com.springboot.essentials2.dto.ClientPutRequestBody;
import br.com.springboot.essentials2.model.Client;
import br.com.springboot.essentials2.service.ClientService;
import br.com.springboot.essentials2.util.ClientCreator;
import br.com.springboot.essentials2.util.DateUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

//@SpringBootTest //Essa anotação faz com q a aplicação seja startada e não somente a classe/método de teste
@ExtendWith(SpringExtension.class) //Essa anotacao junto com o parametro esta indicado q quer usar JUnit com Spring
class ClientControllerTest {

    @InjectMocks //Para a classe em si que será testada
    private ClientController clientController;

    @Mock //Para as dependências da classe que será testada (classes dentro da classe testada)
    private ClientService clientServiceMock;

    @Mock
    private DateUtil dateUtilMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(dateUtilMock.dateFormatter(ArgumentMatchers.any()))
                .thenReturn(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()));

        BDDMockito.when(clientServiceMock.pageClients(ArgumentMatchers.any()))
                .thenReturn(new PageImpl<>(List.of(ClientCreator.createClientGet())));

        BDDMockito.when(clientServiceMock.listAll())
                .thenReturn(List.of(ClientCreator.createClientToBeSaved()));

        BDDMockito.when(clientServiceMock.findClient(ArgumentMatchers.anyInt()))
                .thenReturn(ClientCreator.createClientGet());

        BDDMockito.when(clientServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(ClientCreator.createValidClient()));

        BDDMockito.when(clientServiceMock.saveClient(ArgumentMatchers.any(ClientPostRequestBody.class)))
                .thenReturn(ClientCreator.createClientPost());

//        Esses 2 exemplos sao quando o metodo no controller/service retorna void
        BDDMockito.doNothing().when(clientServiceMock).replaceClient(ArgumentMatchers.any(ClientPutRequestBody.class));
        BDDMockito.doNothing().when(clientServiceMock).deleteClientById(ArgumentMatchers.anyInt());
    }

    @Test
    @DisplayName("pageClients returns list of clients inside page object when successful")
    void pageClients_ReturnsListOfClientsInsidePageObject_WhenSuccessful() {
        String expectedClient = ClientCreator.createClientGet().getName();
        Page<ClientGetFindById> clientPage = clientController.pageClients(null).getBody();

        Assertions.assertThat(clientPage).isNotNull();
        Assertions.assertThat(clientPage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(clientPage.toList().get(0).getName()).isEqualTo(expectedClient);
    }

    @Test
    @DisplayName("listClient returns list of clients when suceessful")
    void listClient_ReturnsListOfClients_WhenSuccessful() {
        String clientName = ClientCreator.createClientToBeSaved().getName();
        List<Client> clientList = clientController.listClient().getBody();

        Assertions.assertThat(clientList).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(clientList.get(0).getName()).isEqualTo(clientName);
    }

    @Test
    @DisplayName("findClientById returns client by id when successful")
    void findClientById_ReturnClientById_WhenSuccessful() {
        ClientGetFindById clientGet = ClientCreator.createClientGet();
        ClientGetFindById clientGetFindById = clientController.findClientById(1).getBody();

        Assertions.assertThat(clientGetFindById).isNotNull();
        Assertions.assertThat(clientGetFindById.getIdClient()).isEqualTo(clientGet.getIdClient()).isNotNull();
        Assertions.assertThat(clientGetFindById.getName()).isEqualTo(clientGet.getName()).isNotEmpty();
    }

    @Test
    @DisplayName("findClientByName returns a list  of client by name when successful")
    void findClientByName_ReturnListOfClientByName_WhenSuccessful() {
        Client client = ClientCreator.createValidClient();
        List<Client> clientList = clientController.findClientByName("Name qualquer").getBody();

        Assertions.assertThat(clientList).isNotNull();
        Assertions.assertThat(clientList.get(0).getName()).isEqualTo(client.getName()).isNotNull();
        Assertions.assertThat(clientList.get(0).getIdClient()).isEqualTo(client.getIdClient());
    }

    @Test
    @DisplayName("findClientByName returns a empty list when client by name is not found")
    void findClientByName_ReturnsEmptyList_WhenClientByNameIsNotFound() {
//        BDDMockito.when(clientServiceMock.findByName(ArgumentMatchers.anyString()))
//                .thenReturn(List.of());
        BDDMockito.when(clientServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<Client> clientList = clientController.findClientByName("Name qualquer").getBody();

        Assertions.assertThat(clientList).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("saveClient returns client when successful")
    void saveClient_ReturnsClient_WhenSuccessful() {
        ClientPostRequestBody clientPost = ClientCreator.createClientPost();
        ClientPostRequestBody client = clientController.saveClient(ClientCreator.createClientPost()).getBody();

        Assertions.assertThat(client).isNotNull();
        Assertions.assertThat(client.getName()).isEqualTo(clientPost.getName()).isNotEmpty();
        Assertions.assertThat(client.getPhone()).isEqualTo(clientPost.getPhone()).isNotEmpty();
    }

    @Test
    @DisplayName("replaceClient updates client when successful")
    void replaceClient_UpdatesClient_WhenSuccessful() {
        Assertions.assertThatCode(() -> clientController.replaceClient(ClientCreator.createClientPut()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> responseHttpUpdateClient = clientController.replaceClient(ClientCreator.createClientPut());

        Assertions.assertThat(responseHttpUpdateClient).isNotNull();
        Assertions.assertThat(responseHttpUpdateClient.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("deleteClient delete client when successful")
    void deleteClient_RemovesClient_WhenSuccessful() {
        Assertions.assertThatCode(() -> clientController.deleteClient(1))
                .doesNotThrowAnyException();

        ResponseEntity<Void> responseHttpDeleteClient = clientController.deleteClient(1);

        Assertions.assertThat(responseHttpDeleteClient).isNotNull();
        Assertions.assertThat(responseHttpDeleteClient.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}