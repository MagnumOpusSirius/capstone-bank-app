package com.project.bankapp.service.admin;

import com.project.bankapp.model.Admin;
import com.project.bankapp.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Optional<Admin> findByUsername(String username){
        return adminRepository.findByUsername(username);
    }
}
