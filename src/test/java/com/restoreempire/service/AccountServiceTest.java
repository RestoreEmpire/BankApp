package com.restoreempire.service;

import static org.mockito.Mockito.verify;

import java.util.Map;

import com.restoreempire.dao.Dao;
import com.restoreempire.exceptions.ValidationException;
import com.restoreempire.model.Account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock Dao<Account> dao;

    Map<String, String[]> map = Map.ofEntries(
        Map.entry("bank", new String[]{"1"}),
        Map.entry("client", new String[]{"1"})
    );
    
    Map<String, String[]> badmap = Map.ofEntries(
        Map.entry("bank", new String[]{"asd"}),
        Map.entry("client", new String[]{})
    );

    @Test
    void testCreate() {
        Account account = new Account();
        account.setBankId(1L);
        account.setClientId(1L);
        account.setFunds("123");
        AccountService service = new AccountService(dao);
        service.create(account);
        verify(dao, Mockito.times(1)).create(account);
    }

    @Test
    void testSetParams() {
        AccountService service = new AccountService(dao);
        Assertions.assertDoesNotThrow(() -> service.setParams(map));
        Assertions.assertThrows(ValidationException.class, () -> service.setParams(badmap));

    }

    @Test
    void testUpdate() {
        AccountService service = new AccountService(dao);
        Account account = new Account();
        account.setBankId(1L);
        account.setClientId(1L);
        account.setFunds("123");
        Account updated = new Account();
        updated.setId(1L);
        service.update(updated, account);
        Mockito.verify(dao, Mockito.times(1)).update(updated.getId(), account);;
    }

    @Test
    void testValidation() {
        Account account = new Account();

        Assertions.assertThrows(ValidationException.class, () -> account.setBankId(0L));
        Assertions.assertThrows(ValidationException.class, () -> account.setClientId(0L));
        Assertions.assertThrows(ValidationException.class, () -> account.setAccountNumber("1"));
        Assertions.assertThrows(ValidationException.class, () -> account.setAccountNumber("abs"));
        Assertions.assertThrows(ValidationException.class, () -> account.setAccountNumber("a".repeat(16)));
        Assertions.assertDoesNotThrow( () -> account.setAccountNumber("1".repeat(16)));
        Assertions.assertThrows(ValidationException.class, () -> account.setFunds("-1"));
        Assertions.assertDoesNotThrow( () -> account.setFunds("1".repeat(16)));
        Assertions.assertDoesNotThrow( () -> account.setFunds("1.12"));
        
    }
}
