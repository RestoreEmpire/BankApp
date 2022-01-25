package model;

import java.time.LocalDate;
import java.util.HashMap;

import processing.data.validators.DataValidation;
import processing.data.validators.DataValidation.nameType;

public abstract class Person {

    private String firstName;
    private String surname;
    private String middlename;
    private LocalDate birthDate;

    public void setFirstName(String name) {
        this.firstName = DataValidation.validateName(name, nameType.FIRST) ? name : firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setSurname(String surname) {
        this.surname = DataValidation.validateName(surname,  nameType.LAST) ? surname : this.surname; 
    }

    public String getSurname() {
        return surname;
    }

    public void setMiddlename(String middlename) {
        this.middlename = DataValidation.validateName(middlename, nameType.MIDDLE) ? middlename : this.middlename; 
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

    public String getBirthDate() {
        return birthDate.toString();
    }

    public String getFullName() {
        return firstName + " " + surname + " " + middlename;
    }



    public void setAllInfo(String firstName, String surname, String middlename, String birthDate) {
        setFirstName(firstName);
        setSurname(surname);
        setMiddlename(middlename);
        setBirthDate(birthDate);
    }

    public void setAllInfo(HashMap<String, String> dict){

        // TODO: сделать реализацию этого метода через словарь
    }

    

    @Override
    public String toString() {   
        return getFullName();
    }

}