package com.project.bankapp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @NotNull
    private String phone;

    @NotNull
    private String pan;

    @NotNull
    private String aadhar;

    @NotNull
    private String secretQuestion;

    @NotNull
    private String secretAnswer;

    //constructor
    public Customer(){

    }

    //constructor
    public Customer(String username, String fullName, String password){
        this.username = username;
        this.fullName = fullName;
        this.password = password;
    }
}
