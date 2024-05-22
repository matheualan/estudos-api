package com.salescontrol.dto.client;

import com.salescontrol.dto.order.OrderPostRequestBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientWithOrderPostDTO {

    private String name;
    private String cpf;
    private List<OrderPostRequestBody> orders;

}
