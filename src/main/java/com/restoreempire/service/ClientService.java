package com.restoreempire.service;

import java.util.Map;

import com.restoreempire.dao.Dao;
import com.restoreempire.exceptions.ValidationException;
import com.restoreempire.model.Client;
import com.restoreempire.service.validators.Validation;

public class ClientService extends BaseService<Client> {

    public ClientService(Dao<Client> dao) {
        setDao(dao);
    }

    @Override
    public void update(Client updated, Client updating) throws ValidationException {
        getDao().update(updated.getId(),updating);
    }
    
    @Override
    public void create(Client client) throws ValidationException {
        getDao().create(client);
        
    }

    public Client setParams(Map<String, String[]> map) {
        var resultMap = check(map);
        if (
            !Validation.isNullOrEmpty(resultMap.get("surname")) &&          
            !Validation.isNullOrEmpty(resultMap.get("firstname")) && 
            !Validation.isNullOrEmpty(resultMap.get("birthdate"))
            ) {
                Client client = new Client();
                client.setSurname(resultMap.get("surname"));
                client.setFirstName(resultMap.get("firstname"));
                client.setBirthDate(resultMap.get("birthdate"));
                if (
                    !Validation.isNullOrEmpty(resultMap.get("middlename"))
                ) {
                    client.setMiddlename(resultMap.get("middlename"));
                }
                if (
                    !Validation.isNullOrEmpty(resultMap.get("client-number"))
                ) {
                    client.setClientNumber(resultMap.get("client-number"));
                }
                else {
                    client.setRandClientNumber();
                }
                return client;

        }
        else throw new ValidationException("Wrong form input");
    }

    
}
