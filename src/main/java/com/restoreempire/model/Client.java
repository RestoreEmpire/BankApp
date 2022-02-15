package com.restoreempire.model;

import java.time.LocalDate;

import com.restoreempire.exceptions.ValidationException;
import com.restoreempire.service.generators.ClientIdGenerator;

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

    public void setClientNumber(String clientNumber) throws ValidationException{
        if (clientNumber.length() == 12)
        this.clientNumber = clientNumber;
        else
            throw new ValidationException("Wrong client number");
    }
    public void setRandClientNumber() {
        clientNumber = new ClientIdGenerator().generate();
    }

    public String getClientNumber() {
        return clientNumber;
    }    
}

