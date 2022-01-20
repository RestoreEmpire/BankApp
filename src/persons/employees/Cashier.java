package persons.employees;

import application.Client;
import application.Person;

public class Cashier extends Person implements Employee {

    @Override
    public String checkClientInfo(Client client) {
        return null;
    }
}
