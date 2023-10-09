package com.project.bankapp.dto.account;

import com.project.bankapp.model.Customer;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name="ACCOUNT_TBL")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType accountType;

    @Column(nullable = false)
    private BigDecimal accountBalance;

    @Column(nullable = false)
    private String approved;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;

    public Account(){

    }

    public Account(Customer customer, AccountType accountType, BigDecimal accountBalance, String approved){
        this.customer = customer;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
        this.approved = approved;
    }
}
