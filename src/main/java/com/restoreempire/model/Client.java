package com.restoreempire.model;

import java.time.LocalDate;

import com.restoreempire.exceptions.ValidationException;
import com.restoreempire.service.generators.ClientIdGenerator;
import com.restoreempire.service.validators.Validation;

/** Model that represents client. Extends from {@code Person}. 
 * Contains Client Number (also known as client id) */
public class Client extends Person {

    private String clientNumber;
   
    public Client(){

    }

    public Client(long id,  String clientNumber, String surname, String firstName, String middlename, String birthDate) {
        setAllInfo(firstName, surname, middlename, birthDate);
        setId(id);
        setClientNumber(clientNumber);
        
    }
    public Client(long id,  String clientNumber, String surname, String firstName, String middlename, LocalDate birthDate) {
        setAllInfo(firstName, surname, middlename, birthDate);
        setId(id);
        setClientNumber(clientNumber);

    }

    public void setClientNumber(String clientNumber) throws ValidationException { 
        this.clientNumber = Validation.validateId(clientNumber, 12) ? clientNumber : this.clientNumber;
    }

    /** This method setting up random client number. Shouldn't be there, but simplicity first. */
    public void setRandClientNumber() {
        clientNumber = new ClientIdGenerator().generate();
    }

    public String getClientNumber() {
        return clientNumber;
    }    
}

