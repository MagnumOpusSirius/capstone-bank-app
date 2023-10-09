package com.project.bankapp.controller;

import com.project.bankapp.dto.LoginRequest;
import com.project.bankapp.dto.response.JwtResponse;
import com.project.bankapp.model.Customer;
import com.project.bankapp.model.ERole;
import com.project.bankapp.model.User;
import com.project.bankapp.repository.CustomerRepository;
import com.project.bankapp.security.jwt.JwtTokenProvider;
import com.project.bankapp.service.CustomerService;
import com.project.bankapp.service.CustomerServiceImpl;
import com.project.bankapp.service.customuserdetail.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class CustomerAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsService;
    // hello
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ======================= Register =======================
    @PostMapping("/register")
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

    // ======================= Login =======================
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateCustomer(@Valid @RequestBody LoginRequest loginRequest){
        try{
            //authenticate the customer using the provided credentials
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            //set the authenticated authentication object in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            //generate a JWT token for the authenticated customer
            String jwt = jwtTokenProvider.generateJwtToken(authentication);

            //get the customer details from the authentication object
            User userDetails = (User) authentication.getPrincipal();

            //get the roles from the customer details
            Set<String> roles = userDetails.getRoles().stream()
                    .map(role -> role.getName().toString())
                    .collect(Collectors.toSet());

            //return the JWT token in the response
            return ResponseEntity.ok(new JwtResponse(userDetails.getUserId(),jwt, userDetails.getUsername(), roles));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username/password supplied!");
        }
    }

}
