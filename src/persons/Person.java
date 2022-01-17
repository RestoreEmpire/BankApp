package persons;

import java.time.LocalDate;
import java.util.HashMap;

import data.processing.DataValidation;

public abstract class Person {

    private String firstName;
    private String surname;
    private String middlename;
    private LocalDate birthDate;
    
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setFirstName(String name) {
        this.firstName = DataValidation.validateName(name) ? name : firstName;
    }

    public void setSurname(String surname) {
        this.surname = DataValidation.validateName(surname) ? surname : this.surname; 
    }
  
    public void setMiddlename(String middlename) {
        this.middlename = DataValidation.validateName(middlename) ? middlename : this.middlename; 
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = LocalDate.parse(birthDate);
    }

    public String getFullName() {
        return firstName + " " + surname + " " + middlename;
    }

    public String getBirthDate() {
        return birthDate.toString();
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

}
