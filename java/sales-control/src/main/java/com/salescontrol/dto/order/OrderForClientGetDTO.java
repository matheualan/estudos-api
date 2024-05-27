package com.salescontrol.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salescontrol.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderForClientGetDTO {

//    private Client client;

    private Double quantity;

    private BigDecimal price;

    private Double totalQuantity = 0.0;

    private BigDecimal totalPurchased = BigDecimal.ZERO;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt = LocalDateTime.now();

}
