package com.banking.product.service;

import com.banking.product.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface ProductService {
    Mono<Product> addProduct(Product product);
    Flux<Product> getProductsByCustomerId(Long customerId);

    Mono<Product> getProductById(Long productId);
    Mono<Product> updateBalance(Long productId, BigDecimal amount);
}
