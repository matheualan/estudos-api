package br.com.springboot.essentials2.repository;

import br.com.springboot.essentials2.model.Client;
import br.com.springboot.essentials2.util.ClientCreator;
import jakarta.validation.ConstraintViolationException;
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
    ClientRepository clientRepository;

    @Test
    @DisplayName("Saved client when successful")
    void save_PersistClient_WhenSuccessful() {
//        Client client = ClientCreator.createClientToBeSaved();
        Client clientSaved = clientRepository.save(ClientCreator.createClientToBeSaved());

        Assertions.assertThat(clientSaved).isNotNull();
        Assertions.assertThat(clientSaved.getIdClient()).isNotNull();
        Assertions.assertThat(clientSaved.getName()).isEqualTo("Clientinho");
        Assertions.assertThat(clientSaved.getPhone()).isEqualTo("11988332211");
    }

    @Test
    @DisplayName("Updated client when successful")
    void updateClient_WhenSuccessful() {
        Client client = ClientCreator.createClientToBeSaved();
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
        Client client = ClientCreator.createClientToBeSaved();
        Client savedClient = clientRepository.save(client);

        clientRepository.delete(savedClient);
        Optional<Client> clientById = clientRepository.findById(savedClient.getIdClient());

        Assertions.assertThat(clientById.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Find client by name when successful")
    void findClientByName_WhenSuccessful() {
        Client client = ClientCreator.createClientToBeSaved();
        Client savedClient = clientRepository.save(client);

        List<Client> clientsByName = clientRepository.findByName(savedClient.getName());

        Assertions.assertThat(clientsByName).isNotEmpty().contains(savedClient);
    }

    @Test
    @DisplayName("Return empty list when client is not found")
    void findByName_ReturnsEmptyList_WhenClientIsNotFound() {
        List<Client> plugin = clientRepository.findByName("Plugin");

        Assertions.assertThat(plugin.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is empty")
    void save_ThrowException_WhenNameIsEmpty() {
        var client = new Client();

//        Assertions.assertThatThrownBy(() -> clientRepository.save(client))
//                .isInstanceOf(ConstraintViolationException.class);

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> clientRepository.save(client))
                .withMessageContaining("O campo name não pode ser nulo nem vazio.");
    }

}