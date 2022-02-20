package com.restoreempire.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Map;

import com.restoreempire.dao.Dao;
import com.restoreempire.exceptions.ValidationException;
import com.restoreempire.model.Bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class BankServiceTest {

    @Mock Dao<Bank> dao;
    @Mock Map<String,String[]> mapmock;
    
    Map<String,String[]> map1 = Map.ofEntries(Map.entry("name", new String[]{"name"}));
    Map<String,String[]> map2 = Map.ofEntries(Map.entry("noname", new String[]{"name"}));
    @Mock BankService service2;
    
    
    
    

    @Test
    void testCreation() {
        Bank bank = new Bank("name");
        BankService service = new BankService(dao);

        Assertions.assertEquals(bank.getName(), service.setParams(map1).getName());
        Assertions.assertThrows(ValidationException.class,() -> service.setParams(map2));
        service.create(bank);
        verify(dao, times(1)).create(bank);
    }

    @Test
    void testUpdate() {
        BankService service = new BankService(dao);
        Bank updating = new Bank("Test");
        Bank updated = service.setParams(map1);
        updated.setId(1);
        service.update(updated, updating);
        verify(dao, times(1)).update(updated.getId(), updating);
    }

    @Test
    void testNameValidation() {
        Bank bank = new Bank();
        Assertions.assertThrows(ValidationException.class, () -> bank.setName(""));
        Assertions.assertThrows(ValidationException.class, () -> bank.setName("a".repeat(130)));
        Assertions.assertThrows(ValidationException.class, () -> bank.setName(null));
        Assertions.assertDoesNotThrow(() -> bank.setName("bank"));
    }
}
