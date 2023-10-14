package com.project.bankapp.dto.account;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTION_TBL")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long transactionId;

    private LocalDateTime date;
    private String reference;
    private Double amount;
    private TransactionType dbCr;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
