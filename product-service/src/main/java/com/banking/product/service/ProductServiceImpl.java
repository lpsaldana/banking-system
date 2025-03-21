package com.banking.product.service;

import com.banking.product.model.Product;
import com.banking.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Mono<Product> addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Flux<Product> getProductsByCustomerId(Long customerId) {
        return productRepository.findByCustomerId(customerId);
    }

    public Mono<Product> getProductById(Long productId) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")));
    }

    // New method to update balance
    public Mono<Product> updateBalance(Long productId, BigDecimal amount) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")))
                .flatMap(product -> {
                    BigDecimal newBalance = product.getBalance().add(amount); // amount can be negative
                    if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
                        return Mono.error(new RuntimeException("Insufficient balance"));
                    }
                    product.setBalance(newBalance);
                    return productRepository.save(product);
                });
    }
}
