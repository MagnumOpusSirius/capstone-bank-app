package com.project.bankapp.controller.customeraccount;

/*
    * STEP:
    * Create an API endpoint to handle POST request for creating a new account
    * This endpoint should accept a json payload for creating a new account and return an appropriate response
 */

import com.project.bankapp.dto.account.Account;
import com.project.bankapp.dto.account.CreateAccountRequest;
import com.project.bankapp.dto.response.AccountResponse;
import com.project.bankapp.service.accountService.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/customer/{customerId}/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    // ======================= Create Account =======================
    @PostMapping("/")
    public ResponseEntity<?> createAccount(@PathVariable Long customerId, @RequestBody CreateAccountRequest createAccountRequest){
        try{
            Account createdAccount = accountService.createAccount(customerId, createAccountRequest);
            // Create a success response DTO:
            AccountResponse accountResponse = new AccountResponse(createdAccount);
            return ResponseEntity.ok(accountResponse);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Account cannot be created");
        }
    }



}
