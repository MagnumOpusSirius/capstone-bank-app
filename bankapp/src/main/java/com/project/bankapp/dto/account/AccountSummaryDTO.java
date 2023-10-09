package com.project.bankapp.dto.account;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountSummaryDTO {

    private Long accountNumber;
    private AccountType accountType;
    private BigDecimal accountBalance;
    private AccountStatus accountStatus;


}
