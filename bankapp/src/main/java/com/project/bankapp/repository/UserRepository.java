package com.project.bankapp.repository;

import com.project.bankapp.model.ERole;
import com.project.bankapp.model.Role;
import com.project.bankapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
