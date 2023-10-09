package com.project.bankapp.dto.account;

import lombok.Data;
import lombok.Getter;

@Getter
public enum AccountStatus {
    ENABLE("Enable"),
    DISABLE("Disable");

    private final String label;

    AccountStatus(String label) {
        this.label = label;
    }

}
