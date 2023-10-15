package com.project.bankapp.model;

import com.project.bankapp.dto.account.Account;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="CUSTOMER_TBL")
@Data //this only generates getters and setters
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NotBlank
    private String username;

    @NotBlank
    private String fullName;

    @NotBlank
    private String password;

    @Transient
    private String confirmPassword;

    private String phone;

    private String pan;

    private String aadhar;

    private String secretQuestion;

    private String secretAnswer;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Beneficiary> beneficiaries;

    //constructor
    public Customer(){

    }

    //constructor
    public Customer(String username, String fullName, String password){
        this.username = username;
        this.fullName = fullName;
        this.password = password;
    }

    // add this method to add a beneficiary to the customer's list of beneficiaries
    public void addBeneficiary(Beneficiary beneficiary){
       if(beneficiaries==null){
           beneficiaries = new ArrayList<>();
       }
       beneficiaries.add(beneficiary);
       beneficiary.setCustomer(this);
    }

    public boolean isPasswordConfirmed(){
        return password != null && confirmPassword != null && password.equals(confirmPassword);
    }
}
