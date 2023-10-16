package com.project.bankapp.dto.beneficiary;

import com.project.bankapp.dto.account.AccountType;
import lombok.Data;

@Data
public class BeneficiaryRequest {

    private Long accountNumber;
    private AccountType accountType;
    private String approved;
    private String name;
    private Boolean isActive;

}
