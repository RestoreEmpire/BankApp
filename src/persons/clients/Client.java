package persons.clients;

import data.processing.generators.ClientIdGenerator;
import persons.Person;

public class Client extends Person {

    // TODO: больше информации о клиенте
    private String id;

    public Client(String firstName, String surname, String middlename, String birthDate) {
        setAllInfo(firstName, surname, middlename, birthDate);
        setId();
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

