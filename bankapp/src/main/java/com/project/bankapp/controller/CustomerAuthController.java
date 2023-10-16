package com.project.bankapp.controller;

import com.project.bankapp.controller.customeraccount.AccountController;
import com.project.bankapp.dto.LoginRequest;
import com.project.bankapp.dto.SecretValidationRequest;
import com.project.bankapp.dto.UpdateCustomerRequest;
import com.project.bankapp.dto.account.TransferRequest;
import com.project.bankapp.dto.beneficiary.BeneficiaryRequest;
import com.project.bankapp.dto.beneficiary.BeneficiaryResponse;
import com.project.bankapp.dto.response.CustomerAccountResponse;
import com.project.bankapp.dto.response.JwtResponse;
import com.project.bankapp.exception.InsufficientBalanceException;
import com.project.bankapp.model.Customer;
import com.project.bankapp.model.ERole;
import com.project.bankapp.model.User;
import com.project.bankapp.repository.CustomerRepository;
import com.project.bankapp.security.jwt.JwtTokenProvider;
import com.project.bankapp.service.CustomerService;
import com.project.bankapp.service.CustomerServiceImpl;
import com.project.bankapp.service.beneficiaryService.BeneficiaryService;
import com.project.bankapp.service.customuserdetail.CustomUserDetailsServiceImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.*;
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


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ======================= Register =======================
    @PostMapping("/register")
    public ResponseEntity<?> signUpCustomer(@Valid @RequestBody Customer customer){
        //check if the username is already taken and return status 400
        if(customerRepository.findByUsername(customer.getUsername()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Username is already taken!");
        }
        //check if the password and confirm password match:
        if(!customer.isPasswordConfirmed()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Password and Confirm Password do not match!");
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

//            Customer customer = (Customer) authentication.getPrincipal();
//            Long customerId = customer.getCustomerId();
            //get the customer details from the authentication object
            User userDetails = (User) authentication.getPrincipal();
//            Object principle = authentication.getPrincipal();
//            System.out.println(principle);

            //get the roles from the customer details
            Set<String> roles = userDetails.getRoles().stream()
                    .map(role -> role.getName().toString())
                    .collect(Collectors.toSet());

            //return the JWT token in the response
            return ResponseEntity.ok(new JwtResponse(userDetails.getCustomer().getCustomerId(), jwt, userDetails.getUsername(), roles));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username/password supplied!");
        }
    }

    // ======================= Get Customer By Id =======================
    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long customerId){

        //call the service method to get the customer by id
        Customer customer = customerService.getCustomerById(customerId);

        if(customer == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Sorry Customer With ID " + customerId + " not found");
        }
        Map<String, String> response=new HashMap<>();
        response.put("username", customer.getUsername());
        response.put("fullName", customer.getFullName());
        response.put("phone", customer.getPhone());
        response.put("pan", customer.getPan());
        response.put("aadhar", customer.getAadhar());

        return ResponseEntity.ok(response);
    }

    // ======================= Update Customer By Id =======================
    @PutMapping("/{customerId}")
    public ResponseEntity<?> updateCustomerById(@PathVariable Long customerId, @Valid @RequestBody UpdateCustomerRequest customer){
//        if(!customerService.existsById(customerId)){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("Sorry Customer With ID " + customerId + " not found");
//        }

        //call the service method to update the customer
        customerService.updateCustomer(customerId, customer);

        return ResponseEntity.ok("Customer updated successfully!");
    }

    // ======================= GET customer account with the account ID =======================
    @GetMapping("{customerId}/account/{accountId}")
    public ResponseEntity<?> getCustomerAccountById(@PathVariable Long customerId, @PathVariable Long accountId){
        CustomerAccountResponse accountResponse = customerService.getCustomerAccountByAccountId(customerId, accountId);

        if(accountResponse != null){
            return ResponseEntity.ok(accountResponse);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found!");
        }

    }

    @Autowired
    private BeneficiaryService beneficiaryService;
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AccountController.class);
    // ======================= Add Beneficiary =======================
    @PostMapping("{customerId}/beneficiary")
    public ResponseEntity<?> addBeneficiary(@PathVariable Long customerId, @RequestBody BeneficiaryRequest beneficiaryRequest){
        try {
            String response = beneficiaryService.addBeneficiary(customerId, beneficiaryRequest);
            logger.info("Beneficiary added: {}", response);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            logger.error("Customer not found", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        } catch (Exception e) {
            logger.error("Customer not found", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    // ======================= Delete Beneficiary =======================
    @DeleteMapping("{customerId}/beneficiary/{beneficiaryId}")
    public ResponseEntity<?> deleteBeneficiary(@PathVariable Long customerId, @PathVariable Long beneficiaryId){
        try {
            String response = beneficiaryService.deleteBeneficiary(customerId, beneficiaryId);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Beneficiary not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    // ======================= Get All Beneficiary for given customer id=======================
    @GetMapping("{customerId}/beneficiary")
    public ResponseEntity<List<BeneficiaryResponse>> getAllBeneficiary(@PathVariable Long customerId){
        try {
            List<BeneficiaryResponse> response = beneficiaryService.getAllBeneficiaries(customerId);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // ======================= Transfer amount from account to another =======================
    @PutMapping("/transfer")
    public ResponseEntity<?> transferBetweenAccounts(@Valid @RequestBody TransferRequest transferRequest){
        try {
            customerService.transferBetweenAccounts(transferRequest);
            return ResponseEntity.ok("Transfer Successful");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        } catch (InsufficientBalanceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance");
        }
    }

    // ======================= Validate Secret Question/Answer details =======================
//    @GetMapping("/{username}/forgot/{question}/{answer}")
//    public ResponseEntity<?> validateSecretDetails(@PathVariable String username, @PathVariable String question, @PathVariable String answer, @RequestBody SecretValidationRequest secretValidationRequest){
//        try {
//            String response = customerService.validateSecretDetails(username, secretValidationRequest);
//            return ResponseEntity.ok(response);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sorry your secret details are not matching");
//        }
//    }
}
