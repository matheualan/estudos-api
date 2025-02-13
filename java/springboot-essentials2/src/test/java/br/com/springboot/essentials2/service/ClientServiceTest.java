package br.com.springboot.essentials2.service;

import br.com.springboot.essentials2.dto.ClientGetFindById;
import br.com.springboot.essentials2.dto.ClientPostRequestBody;
import br.com.springboot.essentials2.exception.ClientNotFoundException;
import br.com.springboot.essentials2.model.Client;
import br.com.springboot.essentials2.repository.ClientRepository;
import br.com.springboot.essentials2.util.ClientCreator;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

//@SpringBootTest //Essa anotação faz com q a aplicação seja startada e não somente a classe/método de teste
@ExtendWith(SpringExtension.class) //Essa anotacao junto com o parametro esta indicado q quer usar JUnit com Spring
class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepositoryMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(clientRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(new PageImpl<>(List.of(ClientCreator.createValidClient())));

        BDDMockito.when(clientRepositoryMock.findAll())
                .thenReturn(List.of(ClientCreator.createClientToBeSaved()));

        BDDMockito.when(clientRepositoryMock.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.of(ClientCreator.createValidClient()));

        BDDMockito.when(clientRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(ClientCreator.createValidClient()));

//        BDDMockito.when(clientRepositoryMock.save(ArgumentMatchers.any(Client.class)))
//                .thenReturn(ClientCreator.createValidClient());

        BDDMockito.doNothing().when(clientRepositoryMock).delete(ArgumentMatchers.any(Client.class));
    }

    @Test
    @DisplayName("pageClients returns list of clients inside page object when successful")
    void pageClients_ReturnsListOfClientsInsidePageObject_WhenSuccessful() {
        String expectedClient = ClientCreator.createValidClient().getName();
        Page<ClientGetFindById> clientPage = clientService.pageClients(PageRequest.of(0, 5));

        Assertions.assertThat(clientPage).isNotNull();
        Assertions.assertThat(clientPage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(clientPage.toList().get(0).getName()).isEqualTo(expectedClient);
    }

    @Test
    @DisplayName("listClient returns list of clients when suceessful")
    void listClient_ReturnsListOfClients_WhenSuccessful() {
        String clientName = ClientCreator.createClientToBeSaved().getName();
        List<Client> clientList = clientService.listAll();

        Assertions.assertThat(clientList).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(clientList.get(0).getName()).isEqualTo(clientName);
    }

    @Test
    @DisplayName("findClient returns client by id when successful")
    void findClient_ReturnClientById_WhenSuccessful() {
        Client client = ClientCreator.createValidClient();
        ClientGetFindById clientGetFindById = clientService.findClient(1);
        clientGetFindById.setIdClient(client.getIdClient());

        Assertions.assertThat(clientGetFindById).isNotNull();
        Assertions.assertThat(clientGetFindById.getIdClient()).isEqualTo(client.getIdClient()).isNotNull();
        Assertions.assertThat(clientGetFindById.getName()).isEqualTo(client.getName()).isNotEmpty();
    }

    @Test
    @DisplayName("findClient throws bad request exception when client is not found")
    void findClient_ThrowsBadRequestException_WhenClientIsNotFound() {
        BDDMockito.when(clientRepositoryMock.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(ClientNotFoundException.class)
                .isThrownBy(() -> clientService.findClient(1))
                .withMessageContaining("Cliente não encontrado");
    }

    @Test
    @DisplayName("findClientByName returns a list  of client by name when successful")
    void findClientByName_ReturnListOfClientByName_WhenSuccessful() {
        Client client = ClientCreator.createValidClient();
        List<Client> clientList = clientService.findByName("Name qualquer");

        Assertions.assertThat(clientList).isNotNull();
        Assertions.assertThat(clientList.get(0).getName()).isEqualTo(client.getName()).isNotNull();
        Assertions.assertThat(clientList.get(0).getIdClient()).isEqualTo(client.getIdClient());
    }

    @Test
    @DisplayName("findClientByName returns a empty list when client by name is not found")
    void findClientByName_ReturnsEmptyList_WhenClientByNameIsNotFound() {
//        BDDMockito.when(clientServiceMock.findByName(ArgumentMatchers.anyString()))
//                .thenReturn(List.of());

//        BDDMockito.when(clientService.findByName(ArgumentMatchers.anyString()))
//                .thenReturn(Collections.emptyList());

//        List<Client> clientList = clientRepositoryMock.findByName("Name qualquer");

//        Assertions.assertThat(clientList).isNotNull().isEmpty();

        BDDMockito.when(clientRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<Client> returnFindByName = clientService.findByName("Just Timberlake");

        Assertions.assertThat(returnFindByName).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("saveClient returns client when successful")
    void saveClient_ReturnsClient_WhenSuccessful() {
        ClientPostRequestBody clientPost = ClientCreator.createClientPost();
        ClientPostRequestBody client = clientService.saveClient(clientPost);

        Assertions.assertThat(client).isNotNull();
        Assertions.assertThat(client.getName()).isEqualTo(clientPost.getName()).isNotEmpty();
        Assertions.assertThat(client.getPhone()).isEqualTo(clientPost.getPhone()).isNotEmpty();
    }

    @Test
    @DisplayName("replaceClient updates client when successful")
    void replaceClient_UpdatesClient_WhenSuccessful() {
        Assertions.assertThatCode(() -> clientService.replaceClient(ClientCreator.createClientPut()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("deleteClient delete client when successful")
    void deleteClient_RemovesClient_WhenSuccessful() {
        Assertions.assertThatCode(() -> clientService.deleteClientById(1))
                .doesNotThrowAnyException();
    }

}