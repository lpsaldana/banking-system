package com.banking.transaction.service;

import com.banking.transaction.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface TransactionService {
    Mono<Transaction> createTransfer(Long customerId, Long sourceProductId, Long targetProductId, BigDecimal amount);
    Mono<Transaction> createPayment(Long customerId, Long sourceProductId, Long targetProductId, BigDecimal amount);
    Mono<Transaction> createInvestment(Long customerId, Long sourceProductId, Long targetProductId, BigDecimal amount);
    Flux<Transaction> getTransactionsByCustomerId(Long customerId);
}
