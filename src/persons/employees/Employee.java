package persons.employees;

import application.Client;

// Лучше в абстрактный класс
public interface Employee {
    public String checkClientInfo(Client client);
}
