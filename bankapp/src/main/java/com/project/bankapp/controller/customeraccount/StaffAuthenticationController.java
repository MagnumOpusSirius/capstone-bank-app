package com.project.bankapp.controller.customeraccount;

import com.project.bankapp.dto.LoginRequest;
import com.project.bankapp.dto.response.JwtResponse;
import com.project.bankapp.security.jwt.JwtTokenProvider;
import com.project.bankapp.service.staff.StaffAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/staff")
public class StaffAuthenticationController {

    @Autowired
    private StaffAuthenticationService staffAuthenticationService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest){
        try{
            String jwt = staffAuthenticationService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(new JwtResponse(jwt));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Incorrect username or password");
        }
    }
}
