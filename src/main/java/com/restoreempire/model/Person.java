package com.restoreempire.model;

import java.time.LocalDate;

import com.restoreempire.service.validators.Validation;
import com.restoreempire.service.validators.Validation.nameType;

public abstract class Person extends BaseModel {

    private String firstName;
    private String surname;
    private String middlename;
    private LocalDate birthDate;

    public void setFirstName(String name) {
        this.firstName = Validation.validateName(name, nameType.FIRST) ? name : firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setSurname(String surname) {
        this.surname = Validation.validateName(surname,  nameType.LAST) ? surname : this.surname; 
    }

    public String getSurname() {
        return surname;
    }

    public void setMiddlename(String middlename) {
        this.middlename = Validation.validateName(middlename, nameType.MIDDLE) ? middlename : this.middlename; 
    }

    public String getMiddlename() {
        return middlename;
    }
    
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = LocalDate.parse(birthDate);
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getFullName() {
        return String.format("%s %s %s", getSurname(), getFirstName(), getMiddlename());
    }

    public void setAllInfo(String firstName, String surname, String middlename, String birthDate) {
        setFirstName(firstName);
        setSurname(surname);
        setMiddlename(middlename);
        setBirthDate(birthDate);
    }

    public void setAllInfo(String firstName, String surname, String middlename, LocalDate birthDate) {
        setFirstName(firstName);
        setSurname(surname);
        setMiddlename(middlename);
        setBirthDate(birthDate);
    }



    @Override
    public String toString() {   
        return getFullName();
    }


}
