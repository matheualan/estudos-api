package br.com.controlevendas.dto;

import br.com.controlevendas.model.Address;
import br.com.controlevendas.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    private String firstName;

    private String lastName;

    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

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
