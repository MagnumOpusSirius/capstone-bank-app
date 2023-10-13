package com.project.bankapp.service;

import com.project.bankapp.dto.UpdateCustomerRequest;
import com.project.bankapp.model.Customer;
import org.hibernate.sql.Update;

public interface CustomerService {
    Customer registerCustomer(Customer customer);

    Customer getCustomerById(Long customerId);

    boolean existsById(Long customerId);

    //update customer
    Customer updateCustomer(Long customerId, UpdateCustomerRequest updateCustomerRequest);
}
