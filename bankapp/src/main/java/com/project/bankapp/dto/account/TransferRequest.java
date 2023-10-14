package com.project.bankapp.dto.account;

import com.project.bankapp.model.Customer;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    private Long fromAccountNumber;
    private Long toAccountNumber;
    private BigDecimal amount;
    private String reason;
    private String by; //it represents the customer who is transferring the money
}
