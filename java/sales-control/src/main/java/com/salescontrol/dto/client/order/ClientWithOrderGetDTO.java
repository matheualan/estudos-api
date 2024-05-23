package com.salescontrol.dto.client.order;

import com.salescontrol.dto.order.OrderGetDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientWithOrderGetDTO {

    private String name;
    private String cpf;
    private List<OrderGetDTO> orders;

}
