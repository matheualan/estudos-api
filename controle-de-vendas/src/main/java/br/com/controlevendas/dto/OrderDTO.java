package br.com.controlevendas.dto;

import br.com.controlevendas.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

//classe para trabalhar somente os dados necessários para realizar um pedido
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private UserOrderDTO userOrderDTO;

    private double quantity;

    private BigDecimal price;

    public OrderDTO(Order order) {
        userOrderDTO = new UserOrderDTO(order.getUser());
        quantity = order.getQuantity();
        price = order.getPrice();
    }

}
