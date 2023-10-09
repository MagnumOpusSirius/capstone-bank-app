package com.project.bankapp.dto.account;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

/*
    * This class is responsible for creating a new account
    *
 */
@Data
public class CreateAccountRequest {
    @NotNull(message = "Account type cannot be null")
    private AccountType accountType;

    @NotNull(message = "Account balance cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Account balance must be greater than 0.0")
    private BigDecimal accountBalance;

    @NotBlank(message = "Approved cannot be blank")
    @Pattern(regexp = "^(yes|no)$", message = "Approved status must be 'yes' or 'no'")
    private String approved;

    private Date dateOfCreation;

    public CreateAccountRequest(){

    }

    public CreateAccountRequest(AccountType accountType, BigDecimal accountBalance, String approved, Date dateOfCreation){
        this.accountType = accountType;
        this.accountBalance = accountBalance;
        this.approved = approved;
        this.dateOfCreation = dateOfCreation;
    }


}
