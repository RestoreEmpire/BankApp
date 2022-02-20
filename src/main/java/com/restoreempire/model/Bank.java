package com.restoreempire.model;

import com.restoreempire.exceptions.ValidationException;
import com.restoreempire.service.validators.Validation;

/** Model that represents bank */
public class Bank extends BaseModel {
    
    private String name;

    public Bank(){

    }

    public Bank(String bankName){
        setName(bankName);
    }
    
    public Bank(long id, String bankName){
        setName(bankName);
        setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(Validation.isNullOrEmpty(name))
            throw new ValidationException("Name is null or empty");
        if(name.length() > 128)
            throw new ValidationException("Name is to long. It should be below 128 characters");
        else
            this.name = name; 
    }

    @Override
    public String toString() {
        return name;
    }
    

}
