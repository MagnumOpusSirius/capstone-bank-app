package com.project.bankapp.repository;

import com.project.bankapp.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    //define custom query methods here:

}
