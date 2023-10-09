package com.project.bankapp.dto.account;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Data
public class UpdateAccountRequest {
    @DecimalMin(value = "0.0", inclusive = false, message = "Account balance must be greater than 0.0")
    private BigDecimal accountBalance;

    private String approved;

    public UpdateAccountRequest(){

    }

    public UpdateAccountRequest(BigDecimal accountBalance, String approved){
        this.accountBalance = accountBalance;
        this.approved = approved;
    }
}
