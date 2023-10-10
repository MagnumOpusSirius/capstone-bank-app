package com.project.bankapp.repository;

import com.project.bankapp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    //find admin by username
    Optional<Admin> findByUsername(String username);

}
