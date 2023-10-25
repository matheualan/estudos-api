package br.com.handler.exception.service;

import br.com.handler.exception.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioServiceMock;

    @Mock
    private UsuarioRepository usuarioRepositoryMock;

    @BeforeEach //Pegar os dados fornecido por esse método para usar nos testes
    void setUp() {

    }

}
