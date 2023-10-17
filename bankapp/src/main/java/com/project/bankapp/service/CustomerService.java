package com.project.bankapp.service;

import com.project.bankapp.dto.UpdateCustomerRequest;
import com.project.bankapp.dto.response.CustomerAccountResponse;
import com.project.bankapp.model.Customer;
import org.hibernate.sql.Update;

public interface CustomerService {
    //get customer by username:
    Customer getCustomerByUsername(String username);

    Customer registerCustomer(Customer customer);

    Customer getCustomerById(Long customerId);

    boolean existsById(Long customerId);

    //update customer
    Customer updateCustomer(Long customerId, UpdateCustomerRequest updateCustomerRequest);

    //get customer account by account id
    CustomerAccountResponse getCustomerAccountByAccountId(Long customerId, Long accountId);
}
