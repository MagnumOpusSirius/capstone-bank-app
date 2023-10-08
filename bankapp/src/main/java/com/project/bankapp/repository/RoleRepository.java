package com.project.bankapp.repository;

import com.project.bankapp.model.ERole;
import com.project.bankapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
