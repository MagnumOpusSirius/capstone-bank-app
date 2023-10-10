package com.project.bankapp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/*
    * @To create an admin user with predefined username and password
 */
@Entity
@Table(name = "ADMIN_TBL")
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public Admin(){

    }

    public Admin(String username, String password){
        this.username = username;
        this.password = password;
    }
}
