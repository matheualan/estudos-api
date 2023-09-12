package br.com.controlevendas.dto;

import br.com.controlevendas.model.Address;
import br.com.controlevendas.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

//    @NotBlank(message = "O campo 'Primeiro nome' não pode ser nulo nem vazio")
    @Size(min = 3, max = 50, message = "O campo 'Primeiro nome' deve conter entre 3 a 50 caracteres")
    private String firstName;

//    @NotBlank(message = "O campo 'Último nome' não pode ser nulo nem vazio")
    @Size(min = 3, max = 50, message = "O campo 'Último nome' deve conter entre 3 a 50 caracteres")
    private String lastName;

//    @NotBlank(message = "O campo CPF não pode ser nulo nem vazio")
    @Size(min = 11, max = 14, message = "O campo CPF deve conter entre 11 números")
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    @Valid
    private List<AddressDTO> addressesDTO = new ArrayList<>();

    public UserDTO(User user) {
        firstName = user.getFirstName();
        lastName = user.getLastName();
        cpf = user.getCpf();
        birthDate = user.getBirthDate();
        for (Address a : user.getAddresses()) {
            var addressDTO = new AddressDTO(a);
//            addressDTO.setUserDTO(this);
            addressesDTO.add(addressDTO);
        }
    }

}
