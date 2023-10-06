package com.project.bankapp.service;

import com.project.bankapp.model.Customer;
import com.project.bankapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer registerCustomer(Customer customer) {
        //I can perform validation and additional logic here
        return customerRepository.save(customer);
    }
}
