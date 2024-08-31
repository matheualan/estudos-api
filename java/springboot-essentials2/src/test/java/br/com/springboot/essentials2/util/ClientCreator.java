package br.com.springboot.essentials2.util;

import br.com.springboot.essentials2.dto.ClientGetFindById;
import br.com.springboot.essentials2.dto.ClientPostRequestBody;
import br.com.springboot.essentials2.dto.ClientPutRequestBody;
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

    public static ClientGetFindById createClientGet() {
        return ClientGetFindById.builder()
                .idClient(1)
                .name("Create Client GET")
                .phone("22912345678")
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static ClientPostRequestBody createClientPost() {
        return ClientPostRequestBody.builder()
                .name("Create Client POST")
                .phone("81932324545")
                .build();
    }

    public static ClientPutRequestBody createClientPut() {
        return ClientPutRequestBody.builder()
                .idClientDTO(1)
                .name("Create Client PUT")
                .phone("11988332211")
                .build();
    }

}
