package com.security.service;

import com.security.model.Product;
import com.security.model.ProductEntity;
import com.security.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    List<Product> products = new ArrayList<>();

    private final ProductRepository productRepository;


    public void saveProduct(Product product) {
        products.add(product);
    }

    public Product getProduct(Integer indexProduct) {
        try {
            return products.get(indexProduct);
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("Produto com código " + indexProduct + " não encontrado.");
        }
    }

    public List<Product> listProducts() {
        return products.stream().toList();
    }

    public void deleteProduct(Integer code) {
        Product product = getProduct(code);
        products.remove(product);
    }

    //Entity JPA
    public void save(ProductEntity product) {
        productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.delete(productRepository.findById(id).get());
    }

    public List<ProductEntity> list() {
        return productRepository.findAll();
    }

}
