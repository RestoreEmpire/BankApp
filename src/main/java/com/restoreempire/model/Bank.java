package com.restoreempire.model;

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
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
