package com.restoreempire.service;

import java.util.Map;

import com.restoreempire.dao.Dao;
import com.restoreempire.exceptions.ValidationException;
import com.restoreempire.model.Account;
import com.restoreempire.service.validators.Validation;

public class AccountService extends BaseService<Account> {

    public AccountService(Dao<Account> dao) {
        setDao(dao);
    }

    public Account setParams(Map<String, String[]> map) {
        var resultMap = check(map);
        if (
            !Validation.isNullOrEmpty(resultMap.get("bank"))  && 
            !Validation.isNullOrEmpty(resultMap.get("client"))
        ){
            Account account = new Account();
            account.setBankId(Long.parseLong(resultMap.get("bank")));
            account.setClientId(Long.parseLong(resultMap.get("client")));
            if (!Validation.isNullOrEmpty(resultMap.get("funds"))
            ) {
                String funds = resultMap.get("funds");
                account.setFunds(funds);
            }
            if (
                !Validation.isNullOrEmpty(resultMap.get("account-number"))
            ){
                account.setAccountNumber(resultMap.get("account-number"));
            }
            else{
                account.setRandomAccountNumber();
            }
            return account;
        }
        else 
            throw new ValidationException("Wrong form input");
    }

    public void update(Account updated, Account updating) throws ValidationException {   
        getDao().update(updated.getId(), updating);
    }
    

    @Override
    public void create(Account account) throws ValidationException {
        getDao().create(account);
        
    }

    
}
