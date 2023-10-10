package com.project.bankapp.dto.response;

import lombok.Data;

@Data
public class JwtResponseAdmin {

    private String jwtToken;

    public JwtResponseAdmin(String jwtToken) {
        this.jwtToken = jwtToken;
    }

}
