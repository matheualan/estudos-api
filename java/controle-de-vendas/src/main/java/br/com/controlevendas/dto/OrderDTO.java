package br.com.controlevendas.dto;

import br.com.controlevendas.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

//classe para trabalhar somente os dados necessários para realizar um pedido
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class OrderDTO {

    private UserOrderDTO userOrderDTO;

    private double quantity;

    private BigDecimal price;

    public OrderDTO(Order order) {
//        userOrderDTO = new UserOrderDTO(order.getUser());
        quantity = order.getQuantity();
        price = order.getPrice();
    }

    public OrderDTO(UserOrderDTO userOrderDTO, double quantity, BigDecimal price) {
        this.userOrderDTO = userOrderDTO;
        this.quantity = quantity;
        this.price = price;
    }

}