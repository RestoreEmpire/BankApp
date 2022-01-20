package application;

import logging.Logger;
import logging.Logger.Status;
import processing.data.generators.ClientIdGenerator;

public class Client extends Person implements Model {

    private String id;

    public Client(String firstName, String surname, String middlename, String birthDate) {
        setAllInfo(firstName, surname, middlename, birthDate);
        setId();
        Logger.write("New client was created with name " + getFullName(), Status.OK);
    }

    public void setId() {
        id = new ClientIdGenerator().generate();
    }

    public String getId() {
        return id;
    }

    @Override
    public void create() {
        // TODO Auto-generated method stub
        
    }

    
}

