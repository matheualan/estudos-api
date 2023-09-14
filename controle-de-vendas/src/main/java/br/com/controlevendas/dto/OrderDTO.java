package br.com.controlevendas.dto;

import br.com.controlevendas.model.Order;
import br.com.controlevendas.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

//classe para trabalhar somente os dados necessários para realizar um pedido
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties(value = {"userDTO"})
public class OrderDTO {

    private double quantity;

    private BigDecimal price;

    private UserOrderDTO userOrderDTO;

    public OrderDTO(Order order) {
        quantity = order.getQuantity();
        price = order.getPrice();
        userOrderDTO = new UserOrderDTO(order.getUser());
    }
}
