package com.project.bankapp.dto;

import lombok.Data;

@Data
public class PasswordUpdateRequest {
    private String username;
    private String password;
}
