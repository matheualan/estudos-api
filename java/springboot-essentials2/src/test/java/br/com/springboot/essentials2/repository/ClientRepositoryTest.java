package br.com.springboot.essentials2.repository;

import br.com.springboot.essentials2.model.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Client Repository")
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName("Saved client when successful")
    void save_PersistClient_WhenSuccessful() {
//        Client client = createClient();
        Client clientSaved = clientRepository.save(createClient());

        Assertions.assertThat(clientSaved).isNotNull();
        Assertions.assertThat(clientSaved.getIdClient()).isNotNull();
        Assertions.assertThat(clientSaved.getName()).isEqualTo("Clientinho");
        Assertions.assertThat(clientSaved.getPhone()).isEqualTo("11988332211");
    }

    @Test
    @DisplayName("Updated client when successful")
    void updateClient_WhenSuccessful() {
        Client client = createClient();
        Client savedClient = clientRepository.save(client);

        savedClient.setName("FormaLaTormentaComTusManos");
        Client updatedClient = clientRepository.save(savedClient);

        Assertions.assertThat(updatedClient).isNotNull();
        Assertions.assertThat(updatedClient.getIdClient()).isNotNull();
        Assertions.assertThat(updatedClient.getName()).isEqualTo(savedClient.getName());
    }

    @Test
    @DisplayName("Delete by id client when successful")
    void deleteClient_WhenSuccessful() {
        Client client = createClient();
        Client savedClient = clientRepository.save(client);

        clientRepository.delete(savedClient);
        Optional<Client> clientById = clientRepository.findById(savedClient.getIdClient());

        Assertions.assertThat(clientById.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Find client by name when successful")
    void findClientByName_WhenSuccessful() {
        Client client = createClient();
        Client savedClient = clientRepository.save(client);

        List<Client> clientsByName = clientRepository.findByName(savedClient.getName());

        Assertions.assertThat(clientsByName).isNotEmpty();
        Assertions.assertThat(clientsByName).contains(savedClient);
    }

    @Test
    @DisplayName("Return empty list when client is not found")
    void findByName_ReturnsEmptyList_WhenClientIsNotFound() {
        List<Client> puglin = clientRepository.findByName("Puglin");

        Assertions.assertThat(puglin.isEmpty()).isTrue();
    }

    private Client createClient() {
        return Client.builder()
                .name("Clientinho")
                .phone("11988332211")
                .build();
    }

}