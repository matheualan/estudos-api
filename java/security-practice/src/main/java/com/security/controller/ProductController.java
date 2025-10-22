package com.security.controller;

import com.security.model.Product;
import com.security.model.ProductModel;
import com.security.service.ProductService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/product")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Hidden
    @PostMapping(path = "/createProduct")
    public ResponseEntity<Void> saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Hidden
    @GetMapping(path = "/listProducts")
    public ResponseEntity<List<Product>> listProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.listProducts());
    }

    @Hidden
    @DeleteMapping(path = "/deleteProduct/{code}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer code) {
        productService.deleteProduct(code);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //Entities JPA

    @PostMapping(path = "/create")
    public ResponseEntity<Void> create(@RequestBody ProductModel product) {
        productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(path = "/create-several")
    public ResponseEntity<Void> createSeveral(@RequestBody List<ProductModel> products) {
        List<ProductModel> list = productService.createSeveral(products);
        URI location = URI.create("/product/create-several");
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/page")
    public ResponseEntity<Page<ProductModel>> page(@PageableDefault(page = 0, size = 3,
            direction = Sort.Direction.DESC, sort = "createdAt") Pageable pageable) {
        return ResponseEntity.ok().body(productService.page(pageable));
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<ProductModel>> list() {
        return ResponseEntity.ok().body(productService.list());
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

}
