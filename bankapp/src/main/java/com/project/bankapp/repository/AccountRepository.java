package com.project.bankapp.repository;

import com.project.bankapp.dto.account.Account;
import com.project.bankapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    //find account by customer
    List<Account> findByCustomer(Customer customer);

    //fetching customer for an account:
//    Customer findCustomerByAccount(Account account);

    //find account by account type
    List<Account> findByAccountType(String accountType);

    //find approved accounts
//    List<Account> findByApprovedTrue();

    //find unapproved accounts
//    List<Account> findByApprovedFalse();

    //find account by customer id and account type
//    List<Account> findByCustomerIdAndAccountType(Long customerId, String accountType);

//    List<Account> findByCustomer_CustomerId(Long customerId);
}
