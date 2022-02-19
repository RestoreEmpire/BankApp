package com.restoreempire.service;

import java.util.Map;

import com.restoreempire.dao.Dao;
import com.restoreempire.exceptions.ValidationException;
import com.restoreempire.model.Bank;
import com.restoreempire.service.validators.Validation;

public class BankService extends BaseService<Bank>{

    public BankService(Dao<Bank> dao) {
        setDao(dao);
    }

    
    public void update(Bank updated, Bank updating){
        getDao().update(updated.getId(), updating);
    }

    public Bank setParams(Map<String, String[]> map) throws ValidationException { 
        var resultMap = check(map);  
        if (!Validation.isNullOrEmpty(resultMap.get("name"))){
            Bank replace = new Bank(resultMap.get("name"));
            return replace;
        }
        else throw 
            new ValidationException("Wrong form input");
    }

    @Override
    public void create(Bank bank) throws ValidationException {
        getDao().create(bank);
    }
}
