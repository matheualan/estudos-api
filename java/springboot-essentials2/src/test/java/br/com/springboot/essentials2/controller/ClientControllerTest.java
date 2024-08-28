package br.com.springboot.essentials2.controller;

import br.com.springboot.essentials2.dto.ClientGetFindById;
import br.com.springboot.essentials2.model.Client;
import br.com.springboot.essentials2.service.ClientService;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

//@SpringBootTest //Essa anotação faz com q a aplicação seja startada e não somente a classe/método de teste
@ExtendWith(SpringExtension.class) //Essa anotacao junto com o parametro esta indicado q quer usar JUnit com Spring
class ClientControllerTest {

    @InjectMocks //Para a classe em si que será testada
    private ClientController clientControllerMock;

    @Mock //Para as dependências de classe dentro da classe que será testada
    private ClientService clientServiceMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(clientServiceMock.pageClients(ArgumentMatchers.any()))
                .thenReturn(new PageImpl<>(List.of(ClientCreator.createClientGet())));

        BDDMockito.when(clientServiceMock.listAll())
                .thenReturn(List.of(ClientCreator.createClientToBeSaved()));

        BDDMockito.when(clientServiceMock.findClient(ArgumentMatchers.anyInt()))
                .thenReturn(ClientCreator.createClientGet());

        BDDMockito.when(clientServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(ClientCreator.createValidClient()));
    }

    @Test
    @DisplayName("pageClients returns list of clients inside page object when successful")
    void pageClients_ReturnsListOfClientsInsidePageObject_WhenSuccessful() {
        String expectedClient = ClientCreator.createClientGet().getName();
        Page<ClientGetFindById> clientPage = clientControllerMock.pageClients(null).getBody();

        Assertions.assertThat(clientPage).isNotNull();
        Assertions.assertThat(clientPage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(clientPage.toList().get(0).getName()).isEqualTo(expectedClient);
    }

    @Test
    @DisplayName("listClient returns list of clients when suceessful")
    void listClient_ReturnsListOfClients_WhenSuccessful() {
        String clientName = ClientCreator.createClientToBeSaved().getName();
        List<Client> clientList = clientControllerMock.listClient().getBody();

        Assertions.assertThat(clientList).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(clientList.get(0).getName()).isEqualTo(clientName);
    }

    @Test
    @DisplayName("findClientById returns client by id when successful")
    void findClientById_ReturnClientById_WhenSuccessful() {
        ClientGetFindById clientGet = ClientCreator.createClientGet();
        ClientGetFindById clientGetFindById = clientControllerMock.findClientById(1).getBody();

        Assertions.assertThat(clientGetFindById).isNotNull();
        Assertions.assertThat(clientGetFindById.getId()).isEqualTo(clientGet.getId()).isNotNull();
        Assertions.assertThat(clientGetFindById.getName()).isEqualTo(clientGet.getName()).isNotEmpty();
    }

    @Test
    @DisplayName("findClientByName returns client by name when successful")
    void findClientByName_ReturnClientByName_WhenSuccessful() {
        Client client = ClientCreator.createValidClient();
        List<Client> clientList = clientControllerMock.findClientByName("Name qualquer").getBody();

        Assertions.assertThat(clientList).isNotNull();
        Assertions.assertThat(clientList.get(0).getName()).isEqualTo(client.getName()).isNotNull();
        Assertions.assertThat(clientList.get(0).getIdClient()).isEqualTo(client.getIdClient());
    }

}