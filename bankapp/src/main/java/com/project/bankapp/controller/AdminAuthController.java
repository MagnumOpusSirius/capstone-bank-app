package com.project.bankapp.controller;

import com.project.bankapp.dto.LoginRequest;
import com.project.bankapp.dto.StaffStatusRequest;
import com.project.bankapp.dto.response.JwtResponse;
import com.project.bankapp.dto.response.JwtResponseAdmin;
import com.project.bankapp.dto.response.StaffResponse;
import com.project.bankapp.model.Admin;
import com.project.bankapp.model.Staff;
import com.project.bankapp.model.User;
import com.project.bankapp.security.jwt.JwtTokenProvider;
import com.project.bankapp.service.admin.AdminService;
import com.project.bankapp.service.admin.AdminUserDetails;
import com.project.bankapp.service.staff.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/*
    * Need to implement:
    *
    * POST: /api/admin/authenticate -> Validate the admin is registered or not
    *
    * POST: /api/admin/staff -> Create staff in the system
    *
    * GET: /api/admin/staff -> List all the staff
    *
    * PUT: /api/admin/staff -> Enable or disable staff, based on this staff can login or not
    *
 */
@RestController
@RequestMapping("/api/admin")
@Validated
public class AdminAuthController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;
    // ======================= Authenticate =======================
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) throws Exception {
        Optional<Admin> adminOptional = adminService.findByUsername(authenticationRequest.getUsername());
        if(adminOptional.isEmpty() || !adminOptional.get().getPassword().equals(authenticationRequest.getPassword())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        //authenticate the admin using the authenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword())
        );

        //generate the token
        String token= jwtTokenProvider.generateJwtToken(authentication);

        JwtResponseAdmin response = new JwtResponseAdmin(token);
        //return the token
        return ResponseEntity.ok(response);
    }

    // ======================= CREATE Staff =======================
    @Autowired
    private StaffService staffService;

    @Secured("ROLE_ADMIN") //only admin can access this endpoint
    @PostMapping("/staff")
    public ResponseEntity<?> createStaff(@Valid @RequestBody Staff staff) {
        try{
            //check if the username already exists
            if(staffService.existsByUsername(staff.getStaffUserName())){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Username already exists");
            }
            //register the staff
            Staff savedStaff = new Staff(staff.getStaffUserName(), staff.getStaffFullName(), passwordEncoder.encode(staff.getStaffPassword()));
            staffService.saveStaff(savedStaff);

            //return 200 Ok response
            return ResponseEntity.ok("Staff created successfully");
        }catch (Exception e){
            //return 403 Forbidden response
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error creating staff");
        }
    }

    // ======================= GET Staff =======================
    @Secured("ROLE_ADMIN") //only admin can access this endpoint
    @GetMapping("/staff")
    public ResponseEntity<List<StaffResponse>> getAllStaff(){
        List<StaffResponse> staffList = staffService.getAllStaff();

        if(staffList.isEmpty()){
            return ResponseEntity.ok(Collections.emptyList());
        }else {
            return ResponseEntity.ok(staffList);
        }
    }
    // ======================= UPDATE Staff Status=======================
    @Secured("ROLE_ADMIN") //only admin can access this endpoint
    @PutMapping("/staff")
    public ResponseEntity<?> updateStaffStatus(@RequestBody StaffStatusRequest request) {
        try {
            boolean statusUpdated= staffService.updateStaffStatus(request.getStaffId(), request.getStatus());
            if(statusUpdated){
                return ResponseEntity.ok("Staff status updated successfully");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating staff status");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating staff status");
        }
    }

}
