package persons.employees;

import java.util.HashMap;

import application.Client;
import application.Person;

public class Manager extends Person implements Employee{

    public Client registerClient(String firstName, String surname, String middlename, String birthDate) {
        return new Client(firstName, surname, middlename, birthDate);
    }
    
    // public Account registerNewAccount(Client client) {
    //     return new Account(client);
    // }

    @Override
    public String checkClientInfo(Client client) {
        StringBuilder info = new StringBuilder(client.toString());
        return info.toString();
    }

    public void changeClientInfo(Client client, HashMap<String, String> info) {

    }
}
