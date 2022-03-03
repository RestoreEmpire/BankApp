package com.restoreempire.mvc.repository;

import com.restoreempire.mvc.model.BankDepartment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDepartmentRepository extends JpaRepository<BankDepartment, Long> {
    
}
