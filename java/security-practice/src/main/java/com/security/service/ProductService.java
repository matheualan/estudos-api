package com.security.service;

import com.security.exception.BadRequestException;
import com.security.exception.ResourceNotFoundException;
import com.security.model.Product;
import com.security.model.ProductModel;
import com.security.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    List<Product> products = new ArrayList<>();

    private final ProductRepository productRepository;

    public void save(ProductModel product) {
        if (product == null) {
            throw new BadRequestException("Requisição inválida");
        }
        productRepository.save(product);
    }

    public List<ProductModel> createSeveral(List<ProductModel> products) {
        if (products == null) {
            throw new BadRequestException("Requisição inválida");
        }
        return productRepository.saveAll(products);
    }

    public ProductModel getById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Objeto com o Id: " + id + " nao encontrado." )
        );
    }

    public void delete(Long id) {
        productRepository.delete(productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado")));
    }

    public List<ProductModel> list() {
        return productRepository.findAll();
    }

    public Page<ProductModel> page(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    //CRUD sem banco de dados
//    public void saveProduct(Product product) {
//        products.add(product);
//    }
//
//    public Product getProduct(Integer indexProduct) {
//        try {
//            return products.get(indexProduct);
//        } catch (IndexOutOfBoundsException e) {
//            throw new RuntimeException("Produto com código " + indexProduct + " não encontrado.");
//        }
//    }
//
//    public List<Product> listProducts() {
//        return products.stream().toList();
//    }
//
//    public void deleteProduct(Integer code) {
//        Product product = getProduct(code);
//        products.remove(product);
//    }
}
