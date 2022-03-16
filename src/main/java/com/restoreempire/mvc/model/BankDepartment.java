package com.restoreempire.mvc.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;



// Retail Banking
// Corporate/Commercial Banking
// Global Banking
// Private Banking
// Investment Banking

@Entity
@Table(name = "bank_department")
public class BankDepartment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;


    @NotEmpty(message = "Please provide a name")
    @Column(name = "department_name", nullable = false)
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
