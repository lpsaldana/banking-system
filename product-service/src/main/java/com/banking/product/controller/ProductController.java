package com.banking.product.controller;

import com.banking.product.DTO.BalanceUpdateRequest;
import com.banking.product.model.Product;
import com.banking.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/{customerId}/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> addProduct(@PathVariable("customerId") Long customerId, @RequestBody Product product){
        product.setCustomerId(customerId);
        return productService.addProduct(product);
    }

    @GetMapping("/{customerId}")
    public Flux<Product> getProducts(@PathVariable("customerId") Long customerId){
        return productService.getProductsByCustomerId(customerId);
    }

    @GetMapping("/details/{productId}")
    public Mono<Product> getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @PatchMapping("/{productId}/balance")
    public Mono<Product> updateBalance(@PathVariable Long productId, @RequestBody BalanceUpdateRequest request) {
        return productService.updateBalance(productId, request.getAmount());
    }
}
