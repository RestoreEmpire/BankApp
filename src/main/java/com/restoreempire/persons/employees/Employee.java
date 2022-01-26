package com.restoreempire.persons.employees;

import com.restoreempire.model.Client;

// Лучше в абстрактный класс
public interface Employee {
    public String checkClientInfo(Client client);
}
