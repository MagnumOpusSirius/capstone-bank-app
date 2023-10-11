package com.project.bankapp.dto.response;

import lombok.Data;

@Data
public class JwtResponseStaff {
    private String jwtToken;

    public JwtResponseStaff(String jwtToken) {
        this.jwtToken = jwtToken;
    }

}
