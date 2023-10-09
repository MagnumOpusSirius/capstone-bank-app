package com.project.bankapp.dto.account;

import com.project.bankapp.model.Customer;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

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

    @Column(nullable = false, updatable = false)
    private LocalDateTime dateOfCreation;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Account(){
        this.dateOfCreation = LocalDateTime.now();
    }

    public Account(Customer customer, AccountType accountType, BigDecimal accountBalance, String approved){
        this.customer = customer;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
        this.approved = approved;
        this.dateOfCreation = LocalDateTime.now();
    }

    public boolean isApproved(){
        return this.approved.equals("Yes");
    }
}
