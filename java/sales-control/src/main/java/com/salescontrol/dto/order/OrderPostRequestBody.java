package com.salescontrol.dto.order;

import com.salescontrol.dto.client.ClientWithOrderPostDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPostRequestBody {

    private ClientWithOrderPostDTO clientWithOrderPostDTO;
    private Double quantity;
    private BigDecimal price;

}
