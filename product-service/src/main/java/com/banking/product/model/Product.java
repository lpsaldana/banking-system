package com.banking.product.model;

import com.banking.product.constant.ProductType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Table("products")
public class Product {
    @Id
    private Long id;
    private Long customerId;
    private ProductType type;
    private BigDecimal balance;

    public Product(Long customerId, ProductType type, BigDecimal balance) {
        this.customerId = customerId;
        this.type = type;
        this.balance = balance;
    }
}
