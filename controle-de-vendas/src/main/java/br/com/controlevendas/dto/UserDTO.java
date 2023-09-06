package br.com.controlevendas.dto;

import br.com.controlevendas.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String firstName;

    private String lastName;

    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    public UserDTO(User user) {
        firstName = user.getFirstName();
        lastName = user.getLastName();
        cpf = user.getCpf();
        birthDate = user.getBirthDate();
    }
}
