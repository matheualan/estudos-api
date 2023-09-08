package br.com.controlevendas.service.users;

import br.com.controlevendas.dto.UserDTO;
import br.com.controlevendas.exception.UserNotFoundException;
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
        var user = new User(userDTO);
        userRepository.save(user);
        return userDTO;
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

    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    public void deleteUserByCpf(String cpf) {
        User userByCpf = encontreUsuarioPorCpf(cpf);
        userRepository.delete(userByCpf);
    }

    public User encontreUsuarioPorCpf(String cpf) {
        return userRepository.findUserByCpf(cpf).orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    public UserDTO findUserByCpf(String cpf) {
        return new UserDTO(userRepository.findUserByCpf(cpf)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado.")));
    }

    public List<UserDTO> findAllUsersByFirstName(String firstName) {
        List<UserDTO> listDTO = new ArrayList<>();

        List<User> users = userRepository.findAllUsersByFirstName(firstName)
                .orElseThrow(() -> new UserNotFoundException(""));

        for (User user : users) {
            var userDTO = new UserDTO(user);
            listDTO.add(userDTO);
        }

        return listDTO;
    }

    public UserDTO userUpdate(String cpf, UserDTO userDTO) {
        User userFound = encontreUsuarioPorCpf(cpf);

        userFound.setFirstName(userDTO.getFirstName());
        userFound.setLastName(userDTO.getLastName());
        userFound.setCpf(userDTO.getCpf());
        userFound.setBirthDate(userDTO.getBirthDate());

        userRepository.save(userFound);

        return new UserDTO(userFound);
    }

}
