package com.project.bankapp.service;

import com.project.bankapp.dto.UpdateCustomerRequest;
import com.project.bankapp.model.Customer;
import com.project.bankapp.model.ERole;
import com.project.bankapp.model.Role;
import com.project.bankapp.model.User;
import com.project.bankapp.repository.CustomerRepository;
import com.project.bankapp.repository.RoleRepository;
import com.project.bankapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService{
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
}
