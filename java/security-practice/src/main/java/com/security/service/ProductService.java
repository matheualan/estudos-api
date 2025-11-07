package com.security.service;

import com.security.exception.BadRequestException;
import com.security.exception.ResourceNotFoundException;
import com.security.model.ProductModel;
import com.security.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

//    public void save(ProductModel product) {
//        if (product == null) {
//            throw new BadRequestException("Requisicao invalida");
//        }
//        productRepository.save(product);
//    }

    public ProductModel save(ProductModel product) {
        if (product == null) {
            throw new BadRequestException("Requisicao invalida");
        }
        return productRepository.save(product);
    }

    public List<ProductModel> createSeveral(List<ProductModel> products) {
        if (products == null) {
            throw new BadRequestException("Requisicao invalida");
        }
        return productRepository.saveAll(products);
    }

    @Transactional(readOnly = true)
    public ProductModel getById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Objeto com o Id: " + id + " nao encontrado.")
        );
    }

    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Objeto com o Id: " + id + " nao encontrado.");
        }
        productRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ProductModel> list() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<ProductModel> page(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    //CRUD sem banco de dados
//    List<Product> products = new ArrayList<>();
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
