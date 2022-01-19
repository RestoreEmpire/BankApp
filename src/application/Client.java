package application;

import data.processing.generators.ClientIdGenerator;
import logging.Logger;

public class Client extends Person {

    // TODO: больше информации о клиенте
    private String id;

    public Client(String firstName, String surname, String middlename, String birthDate) {
        setAllInfo(firstName, surname, middlename, birthDate);
        setId();
        Logger.write("New client was created with name " + getFullName());
    }

    public void setId() {
        id = new ClientIdGenerator().generate();
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return  "ID: " + id + '\n' +
                "Name: " + getFullName() + '\n' +
                "Birth Date: " + getBirthDate();
    }
}

