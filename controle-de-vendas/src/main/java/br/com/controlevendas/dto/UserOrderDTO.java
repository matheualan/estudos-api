package br.com.controlevendas.dto;

import br.com.controlevendas.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//classe DTO somente com os dados necessários de um usuario para gerar um pedido (order)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"listOrdersDTO"})
public class UserOrderDTO {

    private String firstName;

    private String lastName;

    private List<OrderDTO> listOrdersDTO = new ArrayList<>();

    public UserOrderDTO(User user) {
        firstName = user.getFirstName();
        lastName = user.getLastName();

//        for (Order order : user.getOrders()) {
//            var orderDTO = new OrderDTO(order);
//            listOrdersDTO.add(orderDTO);
//        }
    }

}
