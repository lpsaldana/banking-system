package com.banking.transaction.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionRequest {
    private Long customerId;
    private Long sourceProductId;
    private Long targetProductId;
    private BigDecimal amount;
}
