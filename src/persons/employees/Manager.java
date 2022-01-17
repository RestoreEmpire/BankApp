package persons.employees;

import java.util.HashMap;

import persons.Person;
import persons.clients.Client;

public class Manager extends Person implements Employee{

    public Client registerClient(String name, String firstName, String surname, String middlename, String birthDate) {
        return new Client(firstName, surname, middlename, birthDate);
    }
    
    @Override
    public String checkClientInfo(Client client) {
        StringBuilder info = new StringBuilder(client.toString());
        return info.toString();
    }

    public void changeClientInfo(Client client, HashMap<String, String> info) {
        // TODO: функциональность
    }
}
