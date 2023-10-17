package com.project.bankapp.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SecretValidationRequest {


    private String secretQuestion;

    private String secretAnswer;

    public SecretValidationRequest() {
    }

    public SecretValidationRequest(String secretQuestion, String secretAnswer) {
        this.secretQuestion = secretQuestion;
        this.secretAnswer = secretAnswer;
    }


}
