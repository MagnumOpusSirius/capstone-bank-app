package com.project.bankapp.controller;

import com.project.bankapp.model.Customer;
import com.project.bankapp.model.ERole;
import com.project.bankapp.repository.CustomerRepository;
import com.project.bankapp.security.jwt.JwtTokenProvider;
import com.project.bankapp.service.CustomerService;
import com.project.bankapp.service.CustomerServiceImpl;
import com.project.bankapp.service.customuserdetail.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;

@RestController
@RequestMapping("/api/customer/authenticate")
public class CustomerAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsService;


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> signUpCustomer(@Valid @RequestBody Customer customer){
        //check if the username is already taken
        if(customerRepository.findByUsername(customer.getUsername()).isPresent()){
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        //create a new Customer's account:
        Customer newCustomer = new Customer(customer.getUsername(), customer.getFullName(),passwordEncoder.encode(customer.getPassword()));

        //save the customer to the database:
        customerService.registerCustomer(newCustomer);

        return ResponseEntity.ok("Customer registered successfully!");
    }

}
