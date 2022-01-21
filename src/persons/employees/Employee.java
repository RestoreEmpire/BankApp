package persons.employees;

import model.Client;

// Лучше в абстрактный класс
public interface Employee {
    public String checkClientInfo(Client client);
}
