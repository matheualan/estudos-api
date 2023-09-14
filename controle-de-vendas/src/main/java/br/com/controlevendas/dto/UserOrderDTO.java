package br.com.controlevendas.dto;

import br.com.controlevendas.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//classe DTO somente com os dados necessários de um usuario para gerar um pedido (order)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderDTO {

    private String firstName;

    private String lastName;

//    private String cpf;

    public UserOrderDTO(User user) {
        firstName = user.getFirstName();
        lastName = user.getLastName();
//        cpf = user.getCpf();
    }

}
