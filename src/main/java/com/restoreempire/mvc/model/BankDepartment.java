package com.restoreempire.mvc.model;

import javax.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "bank_department")
public class BankDepartment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;


    @NotNull
    @Column(name = "department_name")
    private String departmentName;

    public BankDepartment() {
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    

    

}
