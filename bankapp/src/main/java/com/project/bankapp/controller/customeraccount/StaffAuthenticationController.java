package com.project.bankapp.controller.customeraccount;

import com.project.bankapp.dto.LoginRequest;
import com.project.bankapp.dto.response.JwtResponse;
import com.project.bankapp.dto.response.JwtResponseStaff;
import com.project.bankapp.model.Staff;
import com.project.bankapp.security.jwt.JwtTokenProvider;
import com.project.bankapp.service.staff.StaffAuthenticationService;
import com.project.bankapp.service.staff.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/staff")
public class StaffAuthenticationController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private StaffAuthenticationService staffAuthenticationService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception{
//        try{
//            String jwt = staffAuthenticationService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
//            return ResponseEntity.ok(new JwtResponse(jwt));
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Incorrect username or password");
//        }
        Optional<Staff> staffOptional = staffService.findByUsername(loginRequest.getUsername());

        if(staffOptional.isEmpty() || !staffOptional.get().getStaffPassword().equals(loginRequest.getPassword())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        //authenticate the staff member using the authenticationManager
        Staff staff = staffOptional.get();

        //check if the staff status is Enabled or not
        if(!"ENABLED".equals(staff.getStatus())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Staff account is not enabled");
        }

        //auth the staff member using the authenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        //generate the jwt token
        String jwt = jwtTokenProvider.generateJwtToken(authentication);

        JwtResponseStaff response = new JwtResponseStaff(jwt);

        //return the token
        return ResponseEntity.ok(response);
    }
}
