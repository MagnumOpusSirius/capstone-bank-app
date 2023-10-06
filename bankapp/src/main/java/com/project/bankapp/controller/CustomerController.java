package com.project.bankapp.controller;

import com.project.bankapp.model.Customer;
import com.project.bankapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody @Valid Customer customerRequest){
        // You may want to perform additional validation and error handling here
        return new ResponseEntity<>(customerService.registerCustomer(customerRequest), HttpStatus.CREATED);
    }
}
