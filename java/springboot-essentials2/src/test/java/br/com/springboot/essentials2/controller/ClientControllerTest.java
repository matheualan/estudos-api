package br.com.springboot.essentials2.controller;

import br.com.springboot.essentials2.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@SpringBootTest //Essa anotação faz com q a aplicação seja startada e não somente a classe/método de teste
@ExtendWith(SpringExtension.class)
class ClientControllerTest {

    @InjectMocks //Para a classe em si que será testada
    private ClientController clientController;

    @Mock //Para as dependências de classe dentro da classe que será testada
    private ClientService clientService;

    @Test
    void test() {

    }

}