package com.security.service;

import com.security.model.ProductModel;
import com.security.repository.ProductRepository;
import com.security.util.ProductBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepositoryMock;

    private ProductModel product;

    @BeforeEach
    void setUp() {
        product = ProductBuilder.createProduct();
    }

    // AAA - Arrange, Act, Assert
    @Test
    @DisplayName("Should save a ProductModel when successful")
    void shouldSave_WhenSuccessful_ProductModel() {
        BDDMockito.when(productRepositoryMock.save(product)).thenReturn(product);

        productService.save(product);

        assertThat(product.getName()).isEqualTo("Melancia");
        assertThat(product.getPrice()).isEqualByComparingTo("5.00");
    }

    @Test
    void delete() {
    }

    @Test
    void list() {
    }

    @Test
    void createSeveral() {
    }

    @Test
    void page() {
    }
}