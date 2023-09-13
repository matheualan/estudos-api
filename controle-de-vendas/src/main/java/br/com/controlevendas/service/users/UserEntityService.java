package br.com.controlevendas.service.users;

import br.com.controlevendas.model.User;
import br.com.controlevendas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

}
