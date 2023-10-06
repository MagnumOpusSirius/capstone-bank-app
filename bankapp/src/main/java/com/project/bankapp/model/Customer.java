package com.project.bankapp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
