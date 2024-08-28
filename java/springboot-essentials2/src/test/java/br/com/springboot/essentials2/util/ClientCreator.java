package br.com.springboot.essentials2.util;

import br.com.springboot.essentials2.dto.ClientGetFindById;
import br.com.springboot.essentials2.model.Client;

import java.time.LocalDateTime;

public class ClientCreator {

    public static Client createClientToBeSaved() {
        return Client.builder()
                .name("Create Client To Be Saved")
                .phone("11988332211")
                .build();
    }

    public static Client createValidClient() {
        return Client.builder()
                .idClient(1)
                .name("Create Valid Client")
                .phone("11988332211")
                .build();
    }

    public static Client createUpdatedClient() {
        return Client.builder()
                .idClient(1)
                .name("Create Updated Client")
                .phone("11988332211")
                .build();
    }

    public static ClientGetFindById createClientGet() {
        return ClientGetFindById.builder()
                .id(1)
                .name("Create Client GET")
                .phone("22912345678")
                .createdAt(LocalDateTime.now())
                .build();
    }

}
