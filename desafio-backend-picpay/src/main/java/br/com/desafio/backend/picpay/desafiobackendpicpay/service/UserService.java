package br.com.desafio.backend.picpay.desafiobackendpicpay.service;

import br.com.desafio.backend.picpay.desafiobackendpicpay.domain.user.TypeUser;
import br.com.desafio.backend.picpay.desafiobackendpicpay.domain.user.User;
import br.com.desafio.backend.picpay.desafiobackendpicpay.exception.UserBadRequestException;
import br.com.desafio.backend.picpay.desafiobackendpicpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == TypeUser.MERCHANT) {
            throw new Exception("Usuário do tipo MERCHANT não está autorizado a realizar transações.");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente.");
        }
    }

    public User findUserById(Integer id) {
        return userRepository.findUserById(id).orElseThrow(
                () -> new UserBadRequestException("Usuário naoão encontrado.")
        );
    }

}
