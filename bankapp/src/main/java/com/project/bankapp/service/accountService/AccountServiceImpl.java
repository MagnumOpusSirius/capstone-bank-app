package com.project.bankapp.service.accountService;

import com.project.bankapp.dto.account.*;
import com.project.bankapp.model.Customer;
import com.project.bankapp.repository.AccountRepository;
import com.project.bankapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService{

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
        account.setCustomer(customer);

        //save the account to the database
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountById(Long accountId) throws EntityNotFoundException {
        return accountRepository.findById(accountId).orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + accountId));
    }

    @Override
    public Account updateAccount(Long accountId, UpdateAccountRequest updateAccountRequest) throws EntityNotFoundException {
        Account existingAccount = getAccountById(accountId);

        if(updateAccountRequest.getAccountBalance() != null){
            existingAccount.setAccountBalance(updateAccountRequest.getAccountBalance());
        }
        if(updateAccountRequest.getApproved() != null){
            existingAccount.setApproved(updateAccountRequest.getApproved());
        }

        return accountRepository.save(existingAccount);
    }

    @Override
    public void deleteAccount(Long accountId) throws EntityNotFoundException {
        Account existingAccount = getAccountById(accountId);
        accountRepository.delete(existingAccount);
    }

    @Override
    public List<AccountSummaryDTO> getAccountsSummaryByCustomerId(Long customerId) {
        //fetch accounts for the customer by customer Id:
        List<Account> accounts = accountRepository.findByCustomerCustomerId(customerId);

        //convert account entities to account summary DTOs:
        return accounts.stream()
                .map(account -> {
                    AccountSummaryDTO accountSummaryDTO = new AccountSummaryDTO();
                    accountSummaryDTO.setAccountNumber(account.getAccountId());
                    accountSummaryDTO.setAccountType(account.getAccountType());
                    accountSummaryDTO.setAccountBalance(account.getAccountBalance());
                    accountSummaryDTO.setAccountStatus(account.isApproved() ? AccountStatus.ENABLE: AccountStatus.DISABLE);
                    return accountSummaryDTO;
                })
                .collect(Collectors.toList());
    }
}
