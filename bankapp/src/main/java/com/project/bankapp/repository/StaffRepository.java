package com.project.bankapp.repository;

import com.project.bankapp.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByStaffUserName(String staffUserName);
    boolean existsByStaffUserName(String staffUserName);
}
