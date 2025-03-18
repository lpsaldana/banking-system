package com.banking.customer.service;

import com.banking.customer.model.Customer;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Mono<Customer> registerCustomer(Customer customer);
    Mono<String> loginCustomer(String username, String password);

}
