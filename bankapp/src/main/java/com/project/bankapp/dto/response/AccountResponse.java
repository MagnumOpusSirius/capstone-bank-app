package com.project.bankapp.dto.response;

import com.project.bankapp.dto.account.Account;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AccountResponse {
    private String accountType;
    private BigDecimal accountBalance;
    private String approved;
    private Long accountNumber;
    private LocalDateTime dateOfCreation;
    private Long customerId;

    public AccountResponse(){

    }

    public AccountResponse(Account account){
        this.accountType = account.getAccountType().toString();
        this.accountBalance = account.getAccountBalance();
        this.approved = account.getApproved();
        this.accountNumber = account.getAccountId();
        this.dateOfCreation = account.getDateOfCreation();
        this.customerId = account.getCustomer().getCustomerId();
    }

}
