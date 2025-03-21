package com.banking.transaction.repository;

import com.banking.transaction.model.Transaction;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TransactionRepository extends R2dbcRepository<Transaction, Long> {
    Flux<Transaction> findByCustomerId(Long customerId);
}
