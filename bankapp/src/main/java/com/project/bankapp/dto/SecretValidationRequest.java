package com.project.bankapp.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SecretValidationRequest {

    @NotBlank(message = "Secret quesiton is required")
    private String secretQuestion;

    @NotBlank(message = "Secret answer is required")
    private String secretAnswer;

    public SecretValidationRequest() {
    }

    public SecretValidationRequest(String secretQuestion, String secretAnswer) {
        this.secretQuestion = secretQuestion;
        this.secretAnswer = secretAnswer;
    }


}
