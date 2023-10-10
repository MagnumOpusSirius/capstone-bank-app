package com.project.bankapp.dto.response;

import lombok.Data;

import java.util.Set;

@Data
public class JwtResponse {
    private Long id;
    private String jwtToken;
    private String username;
    private Set<String> roles;

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }
    public JwtResponse(Long id, String jwtToken, String username, Set<String> roles) {
        this.id = id;
        this.jwtToken = jwtToken;
        this.username = username;
        this.roles = roles;
    }

}
