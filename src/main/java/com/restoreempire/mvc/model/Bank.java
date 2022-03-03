package com.restoreempire.mvc.model;

import javax.persistence.*;

import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "bank")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;


    @Column(name = "name")
    @NotNull
    private String name;

    public Bank(@NotNull String name) {
        this.name = name;
    }

    public Bank() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return id + name;
    }


}
