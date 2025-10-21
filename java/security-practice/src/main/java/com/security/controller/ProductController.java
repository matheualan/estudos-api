package com.security.controller;

import com.security.model.Product;
import com.security.model.ProductEntity;
import com.security.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(path = "/createProduct")
    public ResponseEntity<Void> saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(path = "/listProducts")
    public ResponseEntity<List<Product>> listProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.listProducts());
    }

    @DeleteMapping(path = "/deleteProduct/{code}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer code) {
        productService.deleteProduct(code);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //Entity JPA
    @PostMapping(path = "/create")
    public ResponseEntity<Void> create(@RequestBody ProductEntity product) {
        productService.save(product);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<ProductEntity>> list() {
        return ResponseEntity.ok().body(productService.list());
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

}
