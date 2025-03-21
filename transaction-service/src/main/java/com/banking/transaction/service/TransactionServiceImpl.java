package com.banking.transaction.service;

import com.banking.transaction.DTO.BalanceUpdateRequest;
import com.banking.transaction.DTO.ProductDTO;
import com.banking.transaction.constant.TransactionType;
import com.banking.transaction.model.Transaction;
import com.banking.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository transactionRepository;
    private final WebClient webClient;

    public TransactionServiceImpl(TransactionRepository transactionRepository, WebClient.Builder webClientBuilder){
        this.transactionRepository = transactionRepository;
        this.webClient = webClientBuilder.baseUrl("http://product-service").build();  // Eureka resolves this
    }

    @Override
    public Mono<Transaction> createTransfer(Long customerId, Long sourceProductId, Long targetProductId, BigDecimal amount) {
        return validateAndUpdateBalances(sourceProductId, targetProductId, amount, TransactionType.TRANSFER)
                .flatMap(success -> {
                    Transaction transaction = new Transaction(customerId, sourceProductId, targetProductId, TransactionType.TRANSFER, amount);
                    return transactionRepository.save(transaction);
                });
    }

    private Mono<Boolean> validateAndUpdateBalances(Long sourceProductId, Long targetProductId, BigDecimal amount, TransactionType type) {
        // Get source product
        Mono<ProductDTO> sourceProductMono = webClient.get()
                .uri("/products/details/{productId}", sourceProductId)
                .retrieve()
                .bodyToMono(ProductDTO.class);

        // For transfers and investments, get target product
        Mono<ProductDTO> targetProductMono = (targetProductId != null)
                ? webClient.get()
                .uri("/products/details/{productId}", targetProductId)
                .retrieve()
                .bodyToMono(ProductDTO.class)
                : Mono.empty();

        return sourceProductMono
                .flatMap(source -> {
                    if (source.getBalance().compareTo(amount) < 0) {
                        return Mono.error(new RuntimeException("Insufficient balance in source account"));
                    }
                    // Debit source
                    return updateProductBalance(sourceProductId, amount.negate())
                            .then(targetProductMono.defaultIfEmpty(new ProductDTO()))
                            .flatMap(target -> {
                                // Credit target (if applicable)
                                if (targetProductId != null && type != TransactionType.PAYMENT) {
                                    return updateProductBalance(targetProductId, amount);
                                }
                                return Mono.just(true);
                            });
                })
                .thenReturn(true);
    }

    // Helper to update product balance
    private Mono<ProductDTO> updateProductBalance(Long productId, BigDecimal amount) {
        return webClient.patch()
                .uri("/products/{productId}/balance", productId)
                .bodyValue(new BalanceUpdateRequest(amount))
                .retrieve()
                .bodyToMono(ProductDTO.class);
    }

    @Override
    public Mono<Transaction> createPayment(Long customerId, Long sourceProductId, Long targetProductId, BigDecimal amount) {
        return null;
    }

    @Override
    public Mono<Transaction> createInvestment(Long customerId, Long sourceProductId, Long targetProductId, BigDecimal amount) {
        return null;
    }

    @Override
    public Flux<Transaction> getTransactionsByCustomerId(Long customerId) {
        return transactionRepository.findByCustomerId(customerId);
    }
}
