package com.project.bankapp.service;

import com.project.bankapp.dto.UpdateCustomerRequest;
import com.project.bankapp.dto.account.Account;
import com.project.bankapp.dto.account.Transaction;
import com.project.bankapp.dto.response.CustomerAccountResponse;
import com.project.bankapp.model.Customer;
import com.project.bankapp.model.ERole;
import com.project.bankapp.model.Role;
import com.project.bankapp.model.User;
import com.project.bankapp.repository.AccountRepository;
import com.project.bankapp.repository.CustomerRepository;
import com.project.bankapp.repository.RoleRepository;
import com.project.bankapp.repository.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService{
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(CustomerServiceImpl.class);
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    @Transactional
    public Customer registerCustomer(Customer customer) {
        //Step 1: Save customer data to CUSTOMER_TBL
        Customer savedCustomer = customerRepository.save(customer);

        //Step 2: Create a user in USERS_TBL using the customers username and password
        User user = new User(savedCustomer.getUsername(), savedCustomer.getPassword());

        //Step 3: Set the users roles:
        Optional<Role> customerRole = roleRepository.findByName(ERole.CUSTOMER);
        Set<Role> roles= new HashSet<>();
        customerRole.ifPresent(roles::add);
        user.setRoles(roles);
        System.out.println("User roles: " + user.getRoles());

        //Step 4: Save the user to USERS_TBL
        userRepository.save(user);

        //Step 5: Return the saved customer
        return savedCustomer;
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    @Override
    public boolean existsById(Long customerId) {
        return customerRepository.existsById(customerId);
    }

    @Override
    public Customer updateCustomer(Long customerId, UpdateCustomerRequest customer) {
        Customer existingCustomer = customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer with id " + customerId + " does not exist!"));

        //update the customer's information:
        existingCustomer.setFullName(customer.getFullName());
        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setPan(customer.getPan());
        existingCustomer.setAadhar(customer.getAadhar());
        existingCustomer.setSecretQuestion(customer.getSecretQuestion());
        existingCustomer.setSecretAnswer(customer.getSecretAnswer());

        //save the updated customer to the database
        return customerRepository.save(existingCustomer);
    }
    @Autowired
    private AccountRepository accountRepository;
    // ======================= Get Customer Account By Account Id =======================
    @Override
    public CustomerAccountResponse getCustomerAccountByAccountId(Long customerId, Long accountId) {
        Optional<Account> accountOptional = accountRepository.findByAccountId(accountId);

        if(accountOptional.isPresent()){
            //Account found, check if it belongs to the customer with the given customerId
            Account account = accountOptional.get();
            if(account.getCustomer().getCustomerId().equals(customerId)) {
                //map the account details to the CustomerAccountResponse object
                return mapAccountToCustomerAccountResponse(account);
            }
        }
        //account not found or account does not belong to the customer with the given customerId
        //throw an exception
        throw new EntityNotFoundException("Account with id " + accountId + " does not exist for customer with id " + customerId);
    }

    private CustomerAccountResponse mapAccountToCustomerAccountResponse(Account account){
        //map the fields and create a CustomerAccountResponse object
        CustomerAccountResponse customerAccountResponse = new CustomerAccountResponse();
        customerAccountResponse.setAccountId(account.getAccountId());
        customerAccountResponse.setAccountType(account.getAccountType());
        customerAccountResponse.setAccountBalance(account.getAccountBalance());
        customerAccountResponse.setAccountStatus(account.getAccountStatus());
        List<Transaction> transactions = new ArrayList<>();

        for(Transaction tx: account.getTransactions()){
            Transaction transaction= new Transaction();
            transaction.setDate(tx.getDate());
            transaction.setReference(tx.getReference());
            transaction.setAmount(tx.getAmount());
            transaction.setDbCr(tx.getDbCr());

            transactions.add(transaction);
        }
        customerAccountResponse.setTransactions(transactions);

        return customerAccountResponse;
    }
}
