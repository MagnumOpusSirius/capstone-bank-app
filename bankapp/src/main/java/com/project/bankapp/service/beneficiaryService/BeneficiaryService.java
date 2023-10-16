package com.project.bankapp.service.beneficiaryService;

import com.project.bankapp.dto.account.Account;
import com.project.bankapp.dto.beneficiary.BeneficiaryRequest;
import com.project.bankapp.dto.beneficiary.BeneficiaryResponse;
import com.project.bankapp.model.Beneficiary;
import com.project.bankapp.model.Customer;
import com.project.bankapp.repository.BeneficiaryRepository;
import com.project.bankapp.repository.CustomerRepository;
import com.project.bankapp.service.CustomerServiceImpl;
import com.project.bankapp.service.accountService.AccountServiceImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BeneficiaryService {
//    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(BeneficiaryService.class);

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BeneficiaryRepository beneficiaryRepository;


    public String addBeneficiary(Long customerId, BeneficiaryRequest beneficiaryRequest) {
        //validate that the customer and account exist

        Optional<Customer> customerOptional = customerRepository.findById(customerId);
//        logger.info("Customer Optional: {}", customerOptional);

        if(customerOptional.isPresent()) {
            Customer customer = customerOptional.get();

            //create a new beneficiary
            Beneficiary beneficiary = new Beneficiary();
            beneficiary.setAccountNumber(beneficiaryRequest.getAccountNumber());
//            logger.info("Beneficiary account number: {}", beneficiary.getAccountNumber());
            beneficiary.setAccountType(beneficiaryRequest.getAccountType());
//            logger.info("Beneficiary account type: {}", beneficiary.getAccountType());

            String isApproved = beneficiaryRequest.getApproved();
            if ("no".equalsIgnoreCase(isApproved)) {
                beneficiary.setApproved("no");
            } else {
                beneficiary.setApproved("yes");
            }

            beneficiary.setName(beneficiaryRequest.getName());
            beneficiary.setIsActive(false);


            //add the beneficiary to the customer's list of beneficiaries
            customer.addBeneficiary(beneficiary);

            //save the customer including the new beneficiary
            customerRepository.save(customer);

            // Log a success message
//            logger.info("Beneficiary with account number {} added successfully", beneficiary.getAccountNumber());
            return "Beneficiary with account number " + beneficiary.getAccountNumber() + " added successfully";
        }else {
            // Log an error message
//            logger.error("Customer with id {} does not exist", customerId);
            return "Customer with id " + customerId + " does not exist";
        }
    }

    // ======================= Delete Beneficiary =======================
    public String deleteBeneficiary(Long customerId, Long beneficiaryId){
        //validate that the customer and account exist
        Beneficiary beneficiary = beneficiaryRepository.findById(beneficiaryId).orElseThrow(() -> new EntityNotFoundException("Beneficiary not found"));

        if(beneficiary.getCustomer().getCustomerId().equals(customerId)){
            // if the beneficiary belongs to the customer, delete the beneficiary
            beneficiaryRepository.deleteById(beneficiaryId);
            return "Beneficiary Deleted Successfully";
        } else{
            throw new EntityNotFoundException("Beneficiary not found");
        }

    }

    // ======================= Get all beneficiary =======================
    public List<BeneficiaryResponse> getAllBeneficiaries(Long customerId){
        //create a list to store the mapped beneficiary response objects
        List<BeneficiaryResponse> beneficiaryResponseList = new ArrayList<>();

        //retrieve the customer by id
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        //check if the customer exists
        if(customerOptional.isPresent()){
            //get the customer
            Customer customer = customerOptional.get();

            //get the list of beneficiaries
            List<Beneficiary> beneficiaryList = customer.getBeneficiaries();

            //loop through the list of beneficiaries
            for(Beneficiary beneficiary: beneficiaryList){
                //create a new beneficiary response object
                BeneficiaryResponse response = new BeneficiaryResponse();

                //set the beneficiary response object properties
                response.setBeneficiaryAccountNumber(beneficiary.getAccountNumber());
                response.setBeneficiaryName(beneficiary.getName());
                response.setActive(beneficiary.getIsActive() ? "yes" : "no");

                //add the beneficiary response object to the list
                beneficiaryResponseList.add(response);
            }
        }
        return beneficiaryResponseList;
    }
}
