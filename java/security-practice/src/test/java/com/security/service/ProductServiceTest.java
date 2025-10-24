package com.security.service;

import com.security.model.ProductModel;
import com.security.repository.ProductRepository;
import com.security.util.ProductBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepositoryMock;

    private ProductModel product;
    private List<ProductModel> listProducts;

    @BeforeEach
    void setUp() {
        product = ProductBuilder.createMelancia();

        listProducts = List.of(ProductBuilder.createMelancia(),
                                ProductBuilder.createAbacate(),
                                ProductBuilder.createManga());
    }

    // AAA - Arrange, Act, Assert
    @Test
    @DisplayName("Method save() should save a ProductModel when successful")
    void shouldSaveProductModel_WhenSuccessful() {
        productService.save(product);

        BDDMockito.verify(productRepositoryMock, Mockito.times(1)).save(product);
        Assertions.assertThatCode(() -> productService.save(product)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Method save() should throw an exception when it is null")
    void shouldThrowAnException_WhenItIsNull() {
        Assertions.assertThatThrownBy(() -> productRepositoryMock.save(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Method Page() should return a pagination when successful")
    void shouldReturnPagination_WhenSuccessful() {
        BDDMockito.when(productRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(new PageImpl<>(listProducts, PageRequest.of(0, 2), listProducts.size()));

        Page<ProductModel> page = productService.page(PageRequest.of(0, 2));

        Assertions.assertThat(page.getContent())
                .isNotEmpty()
                .hasSize(3)
                .extracting(ProductModel::getName)
                .contains("Melancia", "Abacate", "Manga");
    }

    @Test
    @DisplayName("Method delete() should delete product when successful")
    void shouldDeleteProduct_WhenSuccessful() {
        BDDMockito.doNothing().when(productRepositoryMock).delete(ArgumentMatchers.any(ProductModel.class));
        productService.delete(1L);
        BDDMockito.verify(productRepositoryMock, Mockito.times(1)).delete(product);
//        Assertions.assertThatCode(() -> productService.delete(1L)).doesNotThrowAnyException();
    }

    @Test
    void list() {
    }
    @Test
    void createSeveral() {
    }

}