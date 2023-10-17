package com.project.bankapp.dto.beneficiary;

import lombok.Data;

@Data
public class BeneficiaryResponse {
    private Long beneficiaryId;
    private Long beneficiaryAccountNumber;
    private String beneficiaryName;
    private String active;

}
