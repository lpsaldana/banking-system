package com.banking.product.service;

import com.banking.product.model.Product;
import com.banking.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
}
