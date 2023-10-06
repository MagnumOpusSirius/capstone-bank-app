package com.project.bankapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="ROLES_TBL")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role(){

    }
    public Role(ERole name){
        this.name = name;
    }

}
