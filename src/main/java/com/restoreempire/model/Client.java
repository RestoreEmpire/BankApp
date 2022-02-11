package com.restoreempire.model;

import java.time.LocalDate;

import com.restoreempire.processing.data.generators.ClientIdGenerator;

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

    public void setClientNumber(String clientNumber){
        this.clientNumber = clientNumber;
    }
    public void setRandClientNumber() {
        clientNumber = new ClientIdGenerator().generate();
    }

    public String getClientNumber() {
        return clientNumber;
    }    
}

