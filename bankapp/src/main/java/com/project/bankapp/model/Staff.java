package com.project.bankapp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "STAFF_TBL")
@Data
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffId;

    @NotBlank(message = "Staff username is mandatory")
    private String staffUserName;


    @NotBlank(message = "Staff full name is mandatory")
    private String staffFullName;

    @NotBlank(message = "Staff password is mandatory")
    private String staffPassword;

    public Staff(){

    }

    public Staff(String staffUserName, String staffFullName, String staffPassword){
        this.staffUserName = staffUserName;
        this.staffFullName = staffFullName;
        this.staffPassword = staffPassword;
    }

}
