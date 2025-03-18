package com.banking.customer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("customers")
public class Customer {
    @Id
    private Long ID;
    private String username;
    private String name;
    @Column("middle_name")
    private String middleName;
    @Column("last_name")
    private String lastName;
    private String email;
    private String password;

}
