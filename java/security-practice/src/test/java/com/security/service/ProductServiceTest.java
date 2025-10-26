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

import static org.mockito.ArgumentMatchers.any;

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
    void save_shouldSaveProductModel_WhenSuccessful() {
        Assertions.assertThatCode(() -> productService.save(melancia)).doesNotThrowAnyException();

        BDDMockito.verify(productRepositoryMock, Mockito.times(1)).save(melancia);
        BDDMockito.verifyNoMoreInteractions(productRepositoryMock);
    }

    @Test
    @DisplayName("Method save() should throw an exception when it is null")
    void save_shouldThrowAnException_WhenItIsNull() {
        Assertions.assertThatThrownBy(() -> productService.save(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Dados invalidos");

        BDDMockito.verify(productRepositoryMock, Mockito.never()).save(any());
    }

    @Test
    @DisplayName("Should create and return a list of products when successful")
    void createSeveral_shouldCreateAndReturn_AListOfProducts_WhenSuccessful() {
        BDDMockito.when(productRepositoryMock.saveAll(ArgumentMatchers.anyList()))
                .thenReturn(listProducts);

        List<ProductModel> several = productService.createSeveral(listProducts);

        Assertions.assertThat(several)
                .isNotNull()
                .isNotEmpty()
                .hasSize(3)
                .extracting(ProductModel::getName)
                .contains("Melancia", "Abacate", "Manga");
        BDDMockito.verify(productRepositoryMock, BDDMockito.times(1)).saveAll(listProducts);
    }

    @Test
    @DisplayName("Should return a empty list")
    void createSeveral_shouldReturnAEmptyList() {
        List<ProductModel> emptyList = List.of();
        BDDMockito.when(productRepositoryMock.saveAll(ArgumentMatchers.anyList()))
                .thenReturn(emptyList);

        List<ProductModel> result = productService.createSeveral(emptyList);

        Assertions.assertThat(result).isEmpty();
        BDDMockito.verify(productRepositoryMock, BDDMockito.times(1)).saveAll(emptyList);
    }

    @Test
    @DisplayName("Create Several should return exception when list is empty")
    void createSeveral_ShouldReturnException_WhenListIsEmpty() {
        Assertions.assertThatThrownBy(() -> productService.createSeveral(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("null");
    }

    @Test
    @DisplayName("Method delete() should delete product when successful")
    void delete_shouldDeleteProduct_WhenSuccessful() {
        Long id = melancia.getId();
        BDDMockito.when(productRepositoryMock.findById(id)).thenReturn(Optional.of(melancia));

        productService.delete(id);

        BDDMockito.verify(productRepositoryMock, Mockito.times(1)).findById(id);
        BDDMockito.verify(productRepositoryMock, Mockito.times(1)).delete(melancia);
        BDDMockito.verifyNoMoreInteractions(productRepositoryMock);
        Assertions.assertThatCode(() -> productService.delete(id)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Should return a list of products when successful")
    void list_ShouldReturnAListOfProducts_WhenSuccessful() {
        BDDMockito.when(productRepositoryMock.findAll()).thenReturn(listProducts);

        List<ProductModel> list = productService.list();

        Assertions.assertThat(list)
                .isNotNull()
                .isNotEmpty()
                .hasSize(3)
                .extracting(ProductModel::getName)
                .contains("Melancia", "Abacate", "Manga");
        BDDMockito.verify(productRepositoryMock, BDDMockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Method Page() should return a pagination when successful")
    void page_shouldReturnPagination_WhenSuccessful() {
        BDDMockito.when(productRepositoryMock.findAll(any(PageRequest.class)))
                .thenReturn(new PageImpl<>(listProducts, PageRequest.of(0, 2), listProducts.size()));

        Page<ProductModel> page = productService.page(PageRequest.of(0, 2));

        Assertions.assertThat(page.getContent())
                .isNotNull()
                .isNotEmpty()
                .hasSize(3)
                .extracting(ProductModel::getName)
                .contains("Melancia", "Abacate", "Manga");
    }

}