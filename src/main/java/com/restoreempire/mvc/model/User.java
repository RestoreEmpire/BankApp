package com.restoreempire.mvc.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "bank_user")
public class User {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "department", referencedColumnName = "id")
    // private BankDepartment department;

    @NotEmpty
    @Column(nullable = false)
    private String surname;

    @NotEmpty
    @Column(nullable = false)
    private String firstName;

    private String middleName;

    @NotEmpty
    @Length(min=4, max=32)
    @Column(unique = true, nullable = false)
    private String username;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    // @Email
    // private String email;

    private boolean isActive;

    @NotEmpty(message = "Please enter the password")
    @Column(nullable = false)
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    
    public User(String surname, String firstName, String middleName,
            LocalDate birthDate) {
        this.surname = surname;
        this.firstName = firstName;
        this.middleName = middleName;
        setBirthDate(birthDate);
    }
    
    public User() {}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setRole(Role role) {
        if(roles != null)
            roles.add(role);
        else 
            roles = Set.of(role);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }


    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
