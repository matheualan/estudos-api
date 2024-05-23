package com.salescontrol.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.salescontrol.dto.client.order.ClientWithOrderPostDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonIgnoreProperties(value = {"clientWithOrderPostDTO"})
public class OrderPostDTO {

    private ClientWithOrderPostDTO clientWithOrderPostDTO;
    private Double quantity;
    private BigDecimal price;

}
