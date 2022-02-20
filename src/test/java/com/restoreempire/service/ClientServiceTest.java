package com.restoreempire.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Map;

import com.restoreempire.dao.Dao;
import com.restoreempire.exceptions.ValidationException;
import com.restoreempire.model.Client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock Dao<Client> dao;

    Map<String, String[]> map = Map.ofEntries(
        Map.entry("surname", new String[]{"name"}),
        Map.entry("firstname", new String[]{"firstname"}),
        Map.entry("birthdate", new String[]{"1999-01-01"}),
        Map.entry("middlename", new String[]{"middlename"})
    );

    Map<String, String[]> badmap = Map.ofEntries(
        Map.entry("surname", new String[]{"a".repeat(300)}),
        Map.entry("firstname", new String[]{"firstname"}),
        Map.entry("birthdate", new String[]{"1999-01-01"}),
        Map.entry("middlename", new String[]{"middlename"})
    );

    @Test
    void testCreate() {
        Client client = new Client();
        client.setAllInfo("Name", "surname", "middlename", "1999-01-01");
        ClientService service = new ClientService(dao);
        service.create(client);
        verify(dao, times(1)).create(client);

    }

    @Test
    void testSetParams() {
        ClientService service = new ClientService(dao);
        Assertions.assertDoesNotThrow(() -> service.setParams(map));
        Assertions.assertThrows(ValidationException.class,() -> service.setParams(badmap));
    }

    @Test
    void testUpdate() {
        ClientService service = new ClientService(dao);
        Client client = new Client();
        client.setAllInfo("Name", "surname", "middlename", "1999-01-01");
        Client updated = new Client();
        updated.setId(10);
        service.update(updated, client);
        verify(dao, times(1)).update(updated.getId(), client);
    }
    
    @Test
    void testValidation() {
        Client client = new Client();

        Assertions.assertThrows(ValidationException.class, () -> client.setFirstName("a".repeat(400)));
        Assertions.assertThrows(ValidationException.class, () -> client.setFirstName("123"));
        Assertions.assertThrows(ValidationException.class, () -> client.setFirstName("~+"));
        Assertions.assertThrows(ValidationException.class, () -> client.setFirstName("ASd123"));
        Assertions.assertThrows(ValidationException.class, () -> client.setFirstName(""));
        Assertions.assertThrows(ValidationException.class, () -> client.setFirstName(null));
        Assertions.assertDoesNotThrow(() -> client.setFirstName("Asd"));

        Assertions.assertThrows(ValidationException.class, () -> client.setSurname("a".repeat(400)));
        Assertions.assertThrows(ValidationException.class, () -> client.setSurname("123"));
        Assertions.assertThrows(ValidationException.class, () -> client.setSurname("~+"));
        Assertions.assertThrows(ValidationException.class, () -> client.setSurname("ASd123"));
        Assertions.assertThrows(ValidationException.class, () -> client.setSurname(null));
        Assertions.assertThrows(ValidationException.class, () -> client.setSurname(""));
        Assertions.assertDoesNotThrow(() -> client.setSurname("Asd"));

        
        Assertions.assertThrows(ValidationException.class, () -> client.setMiddlename("a".repeat(400)));
        Assertions.assertThrows(ValidationException.class, () -> client.setMiddlename("123"));
        Assertions.assertThrows(ValidationException.class, () -> client.setMiddlename("~+"));
        Assertions.assertThrows(ValidationException.class, () -> client.setMiddlename("ASd123"));
        Assertions.assertThrows(ValidationException.class, () -> client.setMiddlename(""));
        Assertions.assertDoesNotThrow(() -> client.setMiddlename(null));
        Assertions.assertDoesNotThrow(() -> client.setMiddlename("Asd"));
    }
}
