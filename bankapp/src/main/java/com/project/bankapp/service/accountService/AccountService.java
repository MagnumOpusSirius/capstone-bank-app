package com.project.bankapp.service.accountService;

import com.project.bankapp.dto.account.Account;
import com.project.bankapp.dto.account.CreateAccountRequest;
import com.project.bankapp.model.Customer;
import com.project.bankapp.repository.AccountRepository;
import com.project.bankapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Account createAccount(Long customerId, CreateAccountRequest createAccountRequest){
        //find the customer by id
       Optional<Customer> customerOptional = customerRepository.findById(customerId);
       if(customerOptional.isEmpty()){
           throw new EntityNotFoundException("Customer not found with id: " + customerId);
       }

       Customer customer = customerOptional.get();

        //create a new account based on the request data
        Account account = new Account();
        account.setAccountType(createAccountRequest.getAccountType());
        account.setAccountBalance(createAccountRequest.getAccountBalance());
        account.setApproved(createAccountRequest.getApproved());
        account.
    }
}
