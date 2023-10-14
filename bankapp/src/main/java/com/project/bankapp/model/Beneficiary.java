package com.project.bankapp.model;

import com.project.bankapp.dto.account.AccountType;
import lombok.Cleanup;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "BENEFICIARY_TBL")
@Data
public class Beneficiary {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long beneficiaryId;

    @Column(nullable = false, unique = true)
    private Long accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType accountType;

    @Column(nullable = false)
    private String approved;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;


}
