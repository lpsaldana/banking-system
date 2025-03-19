package com.banking.product.service;

import com.banking.product.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<Product> addProduct(Product product);
    Flux<Product> getProductsByCustomerId(Long customerId);
}
