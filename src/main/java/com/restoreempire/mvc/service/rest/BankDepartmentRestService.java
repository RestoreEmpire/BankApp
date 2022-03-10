package com.restoreempire.mvc.service.rest;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.restoreempire.mvc.model.BankDepartment;
import com.restoreempire.mvc.repository.postgres.BankDepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankDepartmentRestService {
    
    @Autowired
    BankDepartmentRepository departmentRepo;

    public List<BankDepartment> findAllDepartments(){
        return departmentRepo.findAll();
    }

    public BankDepartment findById(Long id){
        return departmentRepo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException());
    }

    public void save(BankDepartment dep) {
        departmentRepo.save(dep);
    }

    public void delete(Long id) {
        departmentRepo.deleteById(id);
    }

    public BankDepartment update(BankDepartment newDep, Long id) {
        return departmentRepo.findById(id)
        .map(dep -> {
            dep.setDepartmentName(newDep.getDepartmentName());
            return departmentRepo.save(dep);
        })
        .orElseGet(() -> {
            newDep.setId(id);
            return departmentRepo.save(newDep);
        });
    }
    
}
