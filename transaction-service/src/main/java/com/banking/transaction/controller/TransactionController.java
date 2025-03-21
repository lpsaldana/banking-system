package com.banking.transaction.controller;

import com.banking.transaction.DTO.TransactionRequest;
import com.banking.transaction.model.Transaction;
import com.banking.transaction.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    public final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Transaction> createTransfer(@RequestBody TransactionRequest request) {
        return transactionService.createTransfer(
                request.getCustomerId(),
                request.getSourceProductId(),
                request.getTargetProductId(),
                request.getAmount()
        );
    }
}
