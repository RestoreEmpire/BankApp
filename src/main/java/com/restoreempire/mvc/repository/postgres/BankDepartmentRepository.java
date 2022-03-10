package com.restoreempire.mvc.repository.postgres;

import com.restoreempire.mvc.model.BankDepartment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BankDepartmentRepository extends JpaRepository<BankDepartment, Long> {
    
}
