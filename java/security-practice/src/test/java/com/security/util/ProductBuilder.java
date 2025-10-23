package com.security.util;

import com.security.model.ProductModel;

import java.math.BigDecimal;

public class ProductBuilder {

    public static ProductModel createProduct() {
        return ProductModel.builder()
                .id(2L)
                .name("Melancia")
                .price(new BigDecimal("5.00"))
                .build();
    }

}