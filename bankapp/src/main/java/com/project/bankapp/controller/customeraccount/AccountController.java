package com.project.bankapp.controller.customeraccount;

/*
    * STEP:
    * Create an API endpoint to handle POST request for creating a new account
    * This endpoint should accept a json payload for creating a new account and return an appropriate response
 */

import com.project.bankapp.service.accountService.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer/{customerId}/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
}
