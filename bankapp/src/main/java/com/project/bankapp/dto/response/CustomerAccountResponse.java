package com.project.bankapp.dto.response;

import com.project.bankapp.dto.account.Account;
import com.project.bankapp.dto.account.AccountStatus;
import com.project.bankapp.dto.account.AccountType;
import com.project.bankapp.dto.account.Transaction;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CustomerAccountResponse {
    private Long accountId;
    private AccountType accountType;
    private BigDecimal accountBalance;
    private AccountStatus accountStatus;
    private List<Transaction> transactions;

public CustomerAccountResponse(){

    }

    public CustomerAccountResponse(Account account){
        this.accountId = account.getAccountId();
        this.accountType = account.getAccountType();
        this.accountBalance = account.getAccountBalance();
        this.accountStatus = account.getAccountStatus();
        this.transactions = account.getTransactions();
    }
}

