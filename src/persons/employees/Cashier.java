package persons.employees;

import model.Client;
import model.Person;

public class Cashier extends Person implements Employee {

    @Override
    public String checkClientInfo(Client client) {
        return null;
    }
}
