package com.project.bankapp.security;

import com.project.bankapp.security.jwt.JwtAuthenticationEntryPoint;
import com.project.bankapp.security.jwt.JwtAuthenticationFilter;
import com.project.bankapp.service.customuserdetail.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //security configurations go here

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsService;

    //this is the class that will handle unauthorized requests
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    //this is the class that will validate tokens
    @Bean
    public JwtAuthenticationFilter authenticationJWTTokenFilter(){
        return new JwtAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //configure security of http requests:
        //i want to allow only customers to access /api/customer/**
        //i want to allow only staff to access /api/staff/**
        //i want to allow only admin to access /api/admin/**
        // want to require authentication for other endpoints:
        //I want to add a filter to validate tokens with every request? why: so that when users login, they get a token, and they can use that token to access other endpoints

        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                    .antMatchers("/api/customer/register").permitAll()
                    .antMatchers("/api/customer/authenticate").permitAll()
                    .antMatchers("/api/customer/**").permitAll()
//                    .antMatchers("/api/customer/**").hasRole("CUSTOMER")
//                    .antMatchers("/api/staff/**").hasRole("STAFF")
//                    .antMatchers("/api/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated();
//                .and()
//                .formLogin()
//                    .loginPage("/login")
//                    .loginProcessingUrl("/custom-login")
//                    .defaultSuccessUrl("/dashboard", true)
//                    .permitAll()
//                .and()
//                .logout()
//                    .logoutSuccessUrl("/login?logout") //after logout, redirect to login?logout
//                    .permitAll()
//                .and()
//                .exceptionHandling()
//                    .accessDeniedPage("/403"); //if user tries to access unauthorized page, redirect to 403

        http.addFilterBefore(authenticationJWTTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
