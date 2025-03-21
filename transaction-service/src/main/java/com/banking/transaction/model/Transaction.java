package com.banking.transaction.model;

import com.banking.transaction.constant.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("transactions")
public class Transaction {

    @Id
    private Long id;
    private Long customerId;
    private Long sourceProductId; // For transfers or payments
    private Long targetProductId; // For transfers or investments
    private TransactionType type;
    private BigDecimal amount;
    private LocalDateTime timestamp;

    public Transaction(Long customerId, Long sourceProductId, Long targetProductId, TransactionType type, BigDecimal amount) {
        this.customerId = customerId;
        this.sourceProductId = sourceProductId;
        this.targetProductId = targetProductId;
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }
}
