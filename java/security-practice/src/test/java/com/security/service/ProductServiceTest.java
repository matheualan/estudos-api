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
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepositoryMock;

    private ProductModel melancia;
    private List<ProductModel> listProducts;

    @BeforeEach
    void setUp() {
        melancia = ProductBuilder.createMelancia();
        listProducts = List.of(ProductBuilder.createMelancia(),
                                ProductBuilder.createAbacate(),
                                ProductBuilder.createManga());
    }

    // AAA - Arrange, Act, Assert
    @Test
    @DisplayName("Method save() should save a ProductModel when successful")
    void shouldSaveProductModel_WhenSuccessful() {
        productService.save(melancia);

        BDDMockito.verify(productRepositoryMock, Mockito.times(1)).save(melancia);
        BDDMockito.verifyNoMoreInteractions(productRepositoryMock);
        Assertions.assertThatCode(() -> productService.save(melancia)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Method save() should throw an exception when it is null")
    void shouldThrowAnException_WhenItIsNull() {
        Assertions.assertThatThrownBy(() -> productRepositoryMock.save(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createSeveral() {
    }

    @Test
    @DisplayName("Method delete() should delete product when successful")
    void shouldDeleteProduct_WhenSuccessful() {
        Long id = melancia.getId();
        BDDMockito.when(productRepositoryMock.findById(id)).thenReturn(Optional.of(melancia));

        productService.delete(id);

        BDDMockito.verify(productRepositoryMock, Mockito.times(1)).findById(id);
        BDDMockito.verify(productRepositoryMock, Mockito.times(1)).delete(melancia);
        BDDMockito.verifyNoMoreInteractions(productRepositoryMock);
        Assertions.assertThatCode(() -> productService.delete(id)).doesNotThrowAnyException();
    }

    @Test
    void list() {
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

}