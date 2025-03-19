package com.banking.customer.repository;

import com.banking.customer.model.Customer;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends R2dbcRepository<Customer, Long> {
    Mono<Customer> findByUsername(String username);
}
