package com.project.bankapp.service.staff;

import com.project.bankapp.model.User;
import com.project.bankapp.security.jwt.JwtTokenProvider;
import com.project.bankapp.service.customuserdetail.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class StaffAuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsService;

    public String authenticate(String username, String password) throws Exception{
        try{
            Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return jwtTokenProvider.generateJwtToken(authentication);
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        }

    }

}
