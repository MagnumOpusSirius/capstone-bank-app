package com.project.bankapp.service.accountService;

import com.project.bankapp.dto.account.Account;
import com.project.bankapp.dto.account.AccountSummaryDTO;
import com.project.bankapp.dto.account.CreateAccountRequest;
import com.project.bankapp.dto.account.UpdateAccountRequest;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface AccountService {
    /*
        * Creates a new account for a customer
        * @param customerId
        * @param createAccountRequest
        * @return Account
        * @throws EntityNotFoundException
     */
    Account createAccount(Long customerId, CreateAccountRequest createAccountRequest) throws EntityNotFoundException;

    /*
        * Gets account details by account Id
        * @param accountId
        * @return Account
        * @throws EntityNotFoundException
     */
    Account getAccountById(Long accountId) throws EntityNotFoundException;

    /*
        * Updates an account details by account Id
        * @param accountId
        * @param updateAccountRequest
        * @return Account
        * @throws EntityNotFoundException
     */
    Account updateAccount(Long accountId, UpdateAccountRequest updateAccountRequest) throws EntityNotFoundException;

    /*
        * Deletes an account by account Id
        * @param accountId
        * @throws EntityNotFoundException
     */
    void deleteAccount(Long accountId) throws EntityNotFoundException;

    /*
        * Get a list of accounts associated with a customer
        * @param customerId
        * @return List<Account>
        * @throws EntityNotFoundException
     */
    List<AccountSummaryDTO> getAccountsSummaryByCustomerId(Long customerId);
}
