package br.com.springboot.essentials2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class Client {

    private Integer idClient;

    private String name;

    public Client() {
    }

    public Client(Integer idClient, String name) {
        this.name = name;
        this.idClient = idClient;
    }

}
