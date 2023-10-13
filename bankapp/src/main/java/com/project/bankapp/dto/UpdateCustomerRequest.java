package com.project.bankapp.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateCustomerRequest {
    @NotBlank
    private String fullName;
    @NotBlank
    private String phone;
    @NotBlank
    private String pan;
    @NotBlank
    private String aadhar;
    @NotBlank
    private String secretQuestion;
    @NotBlank
    private String secretAnswer;
}
