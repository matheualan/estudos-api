package br.com.desafio.backend.picpay.desafiobackendpicpay.service;

import br.com.desafio.backend.picpay.desafiobackendpicpay.domain.user.TypeUser;
import br.com.desafio.backend.picpay.desafiobackendpicpay.domain.user.User;
import br.com.desafio.backend.picpay.desafiobackendpicpay.dto.record.UserDTO;
import br.com.desafio.backend.picpay.desafiobackendpicpay.exception.UserBadRequestException;
import br.com.desafio.backend.picpay.desafiobackendpicpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    método para validar transações (cliente pode fazer trans. entre cliente e lojista  mas lojista não pode enviar pra ngm
    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == TypeUser.MERCHANT) {
            throw new Exception("Usuário do tipo MERCHANT(lojista) não está autorizado a realizar transações.");
        } else if (sender.getBalance().compareTo(amount) < 0) { //jogar no chatGPT para entender melhor o resultado dessa condição
            throw new Exception("Saldo insuficiente.");
        }
//
//        if (sender.getBalance().compareTo(amount) < 0) {
//            throw new Exception("Saldo insuficiente.");
//        }
    }

    public User findUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserBadRequestException("Usuário não encontrado.")
        );
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User createUser(UserDTO userDTO) {
        User newUser = new User(userDTO);
        return userRepository.save(newUser);
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }
}
