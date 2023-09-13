package br.com.controlevendas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties(value = {"userDTO"})
public class OrderDTO {

    private double quantity;
    private BigDecimal value;

//    public OrderDTO(Order order) {
//
//    }

}
