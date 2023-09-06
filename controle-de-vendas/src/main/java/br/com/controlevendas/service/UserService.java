package br.com.controlevendas.service;

import br.com.controlevendas.dto.UserDTO;
import br.com.controlevendas.model.User;
import br.com.controlevendas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> saveSeveralUsers(List<UserDTO> usersDTO) {
        List<User> users = new ArrayList<>();
        for (UserDTO userDTO : usersDTO) {
            var user = new User(userDTO);
            users.add(user);
        }
        userRepository.saveAll(users);
        return usersDTO;
    }

    public UserDTO saveUser(UserDTO userDTO) {
        return null;
    }

    public List<UserDTO> listAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            var userDTO = new UserDTO(user);
            usersDTO.add(userDTO);
        }
        return usersDTO;
    }

//    public UserDTO userUpdate(Integer id, UserDTO userDTO) {
//
//    }

}
