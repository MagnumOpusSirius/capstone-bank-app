package com.project.bankapp.service;

import com.project.bankapp.model.Customer;
import com.project.bankapp.model.ERole;
import com.project.bankapp.model.Role;
import com.project.bankapp.model.User;
import com.project.bankapp.repository.CustomerRepository;
import com.project.bankapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Customer registerCustomer(Customer customer) {
        //Step 1: Save customer data to CUSTOMER_TBL
        Customer savedCustomer = customerRepository.save(customer);

        //Step 2: Create a user in USERS_TBL using the customers username and password
        User user = new User(savedCustomer.getUsername(), savedCustomer.getPassword());

        //Step 3: Set the users roles:
        Optional<Role> customerRole = userRepository.findRoleByName(ERole.ROLE_CUSTOMER);
        Set<Role> roles= new HashSet<>();
        customerRole.ifPresent(roles::add);
        user.setRoles(roles);

        //Step 4: Save the user to USERS_TBL
        userRepository.save(user);

        //Step 5: Return the saved customer
        return savedCustomer;
    }
}
