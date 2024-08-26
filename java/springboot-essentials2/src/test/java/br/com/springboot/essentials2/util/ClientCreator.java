package br.com.springboot.essentials2.util;

import br.com.springboot.essentials2.dto.ClientGetFindById;
import br.com.springboot.essentials2.model.Client;

import java.time.LocalDateTime;

public class ClientCreator {

    public static Client createClientToBeSaved() {
        return Client.builder()
                .name("Clientinho")
                .phone("11988332211")
                .build();
    }

    public static Client createValidClient() {
        return Client.builder()
                .idClient(1)
                .name("Clientinho")
                .phone("11988332211")
                .build();
    }

    public static Client createUpdatedClient() {
        return Client.builder()
                .idClient(1)
                .name("Clientao")
                .phone("11988332211")
                .build();
    }

    public static ClientGetFindById createClientGet() {
        return ClientGetFindById.builder()
                .name("Name Loko")
                .phone("22912345678")
                .createdAt(LocalDateTime.now())
                .build();
    }

}
