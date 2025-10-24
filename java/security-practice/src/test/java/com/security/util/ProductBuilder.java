package com.security.util;

import com.security.model.ProductModel;

import java.math.BigDecimal;

public class ProductBuilder {

    public static ProductModel createMelancia() {
        return ProductModel.builder()
                .id(1L)
                .name("Melancia")
                .price(new BigDecimal("5.00"))
                .build();
    }

    public static ProductModel createAbacate() {
        return ProductModel.builder()
                .name("Abacate")
                .price(new BigDecimal("3.00"))
                .build();
    }

    public static ProductModel createManga() {
        return ProductModel.builder()
                .name("Manga")
                .price(new BigDecimal("2.00"))
                .build();
    }

}