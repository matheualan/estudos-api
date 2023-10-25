package br.com.controlevendas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListUserOrderDTO {

    private String firstName;
    private String lastName;
    private List<OrderDTO> listOrders = new ArrayList<>();

}
