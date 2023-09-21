package br.com.controlevendas.dto;

import br.com.controlevendas.model.Order;
import br.com.controlevendas.model.User;
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
//@JsonIgnoreProperties(value = {"listOrdersDTO"})
public class UserOrderDTO {

    private String firstName;

    private String lastName;

    private List<OrderDTO> ordersDTO = new ArrayList<>();

    public UserOrderDTO(User user) {
        firstName = user.getFirstName();
        lastName = user.getLastName();

//        Este trecho de código está causando recursão infinita em chamados de construtores
        for (Order order : user.getOrders()) {
            var orderDTO = new OrderDTO(order);
//            orderDTO.setUserOrderDTO(this);
            ordersDTO.add(orderDTO);
        }
    }

}
