package com.banking.customer.controller;

import com.banking.customer.DTO.LoginRequest;
import com.banking.customer.model.Customer;
import com.banking.customer.service.CustomerService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public Mono<Customer> registerCustomer(@RequestBody Customer customer){
        return customerService.registerCustomer(customer);
    }
    @PostMapping("/login")
    public Mono<String> login(@RequestBody LoginRequest loginRequest){
        return customerService.loginCustomer(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @GetMapping("/profile")
    public Mono<Customer> getProfile() {
        return Mono.just(new Customer(1L, "Test", "test",
                "dsf","asd","test@example.com","hashed"));
    }
}
